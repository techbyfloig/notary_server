package com.techbyfloig.cubannotary.notary.service

import com.techbyfloig.cubannotary.notary.model.AppType
import com.techbyfloig.cubannotary.notary.model.pdf.PdfFieldType
import com.techbyfloig.cubannotary.notary.model.pdf.PdfHandler
import com.techbyfloig.cubannotary.notary.repo.JwtKeyRepo
import com.techbyfloig.cubannotary.notary.repo.PassportRepo
import org.apache.pdfbox.cos.COSDictionary
import org.apache.pdfbox.cos.COSName
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDResources
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Service
class PdfServices(
    private val passportRepo: PassportRepo
) {

    fun getPassportZipPdfs(type: Int): Result<ByteArray> {
        return when (AppType.from(type)) {
            AppType.PASSPORT -> {
                try {
                    val passports = passportRepo.findAll().map {
                        it.toDto()
                    }

                    val pdfs = passports.map {
                        load("templates/passportForm.pdf", it.fields())
                    }

                    if (pdfs.isEmpty()) Result.failure(Exception("No passports found")) else
                        Result.success(createZipWithPdfs(pdfs))
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }

            AppType.RESIDENCE -> {
                Result.success(ByteArray(0))
            }

            else -> {
                Result.failure(Exception("AppType incorrect"))
            }
        }
    }


    private fun createZipWithPdfs(pdfList: List<ByteArray>): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val zipOutputStream = ZipOutputStream(byteArrayOutputStream)

        for ((index, pdf) in pdfList.withIndex()) {
            val entryName = "document$index.pdf"
            val zipEntry = ZipEntry(entryName)
            zipOutputStream.putNextEntry(zipEntry)
            zipOutputStream.write(pdf)
            zipOutputStream.closeEntry()
        }

        zipOutputStream.close()

        return byteArrayOutputStream.toByteArray()

    }

    fun load(
        path: String,
        data: List<PdfFieldType>
    ): ByteArray {

        val passportTemplate = ClassPathResource(path)
        val pdfDoc = PDDocument.load(passportTemplate.inputStream)
        val acroForm = pdfDoc.documentCatalog.acroForm

        // save values
        data.forEach { fieldType ->
            when (fieldType) {
                is PdfFieldType.Text -> handleTextField(fieldType, acroForm)

                is PdfFieldType.CheckBok -> {
                    val fieldBox = acroForm.getField(fieldType.key)
                    fieldBox?.setValue(fieldType.values())
                }
            }
        }

        val byteArrayOutputStream = ByteArrayOutputStream()
        pdfDoc.save(byteArrayOutputStream)
        pdfDoc.close()

        return byteArrayOutputStream.toByteArray()
    }


    private fun handleTextField(fieldType: PdfFieldType.Text, acroForm: PDAcroForm) {
        val fontSize = fieldType.fontSize // Replace with your desired font size
            ?: 11

        val fontDict = COSDictionary()
        fontDict.setItem(COSName.TYPE, COSName.FONT)

        val fieldText = acroForm.getField(fieldType.key) as PDVariableText?
        val resources = fieldText?.acroForm?.defaultResources ?: PDResources()
        fieldText?.acroForm?.defaultResources = resources

        resources.put(COSName.getPDFName("F1"), PDType1Font.COURIER)

        fieldText?.defaultAppearance = "/F1 $fontSize Tf 0 g"
        fieldText?.q = PDVariableText.QUADDING_CENTERED
        fieldText?.setValue(fieldType.values())
    }

}