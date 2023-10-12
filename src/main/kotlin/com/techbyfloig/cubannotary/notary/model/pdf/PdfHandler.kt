package com.techbyfloig.cubannotary.notary.model.pdf

import org.apache.pdfbox.cos.COSDictionary
import org.apache.pdfbox.cos.COSName
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDResources
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText.QUADDING_CENTERED
import org.springframework.core.io.ClassPathResource
import java.io.ByteArrayOutputStream


class PdfHandler {

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

//    fun newPdfReceipt(context: Context, view: View, fileName: String) {
//
//        val pdfDoc = PDDocument()
//        val blankPag = PDPage()
//        pdfDoc.addPage(blankPag)
//
//        //creating the PDPageContentStream object
//        val content = PDPageContentStream(pdfDoc, blankPag)
//
//        //Drawing the image in the PDF document
//        content.drawImage(pdImage, 50F, 0F, 480F, 800F)
//        content.close()
//
//        pdfDoc.save(file)
//        pdfDoc.close()
//
//    }

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
        fieldText?.q = QUADDING_CENTERED
        fieldText?.setValue(fieldType.values())
    }

}