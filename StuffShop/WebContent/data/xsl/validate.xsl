<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://www.example.org/GoodXMLSchema"
	xmlns:class-for-messages="xalan://com.epam.stuffshop.validation.ValidationUtil"
	xmlns:validator="xalan://com.epam.stuffshop.validation.ProductValidator">

	<xsl:param name="productName" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="date" />
	<xsl:param name="color" />
	<xsl:param name="price" />
	<xsl:param name="miss" />
	<xsl:param name="validationUtil" />
	<xsl:variable name="validator" select="validator:new()" />

	<xsl:param name="isNameValid">
		<xsl:value-of select="validator:validateGood($validator, $productName)" />
	</xsl:param>
	<xsl:param name="isProducerValid">
		<xsl:value-of select="validator:validateProducer($validator, $producer)" />
	</xsl:param>
	<xsl:param name="isModelValid">
		<xsl:value-of select="validator:validateModel($validator, $model)" />
	</xsl:param>
	<xsl:param name="isDateValid">
		<xsl:value-of select="validator:validateDateOfIssue($validator, $date)" />
	</xsl:param>
	<xsl:param name="isColorValid">
		<xsl:value-of select="validator:validateColor($validator, $color)" />
	</xsl:param>
	<xsl:param name="isPriceValid">
		<xsl:choose>
			<xsl:when test="$price">
				<xsl:value-of select="validator:validatePrice($validator, $price)" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="validator:validateMissing($validator, $miss)" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:param>
	<xsl:template match="/">
		<xsl:value-of
			select="class-for-messages:isNameValid($validationUtil, $isNameValid)" />
		<xsl:value-of
			select="class-for-messages:isProducerValid($validationUtil, $isProducerValid)" />
		<xsl:value-of
			select="class-for-messages:isModelValid($validationUtil, $isModelValid)" />
		<xsl:value-of
			select="class-for-messages:isDateValid($validationUtil, $isDateValid)" />
		<xsl:value-of
			select="class-for-messages:isColorValid($validationUtil, $isColorValid)" />
		<xsl:value-of
			select="class-for-messages:isPriceValid($validationUtil, $isPriceValid)" />
	</xsl:template>

</xsl:stylesheet>