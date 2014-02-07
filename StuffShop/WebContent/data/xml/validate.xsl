<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://www.example.org/GoodXMLSchema"
	xmlns:class-for-messages="xalan://com.epam.stuffshop.validation.ValidationUtil"
	xmlns:validator="xalan://com.epam.stuffshop.validation.ProductValidator">

	<xsl:param name="subcategoryName" />
	<xsl:param name="categoryName" />
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

		<xsl:variable name="isDatumValid"
			select="class-for-messages:validationResult($validationUtil)" />

		<xsl:choose>
			<xsl:when test="$isDatumValid = 'true'">
				<xsl:apply-templates />
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="addProductFailed" />
			</xsl:otherwise>
		</xsl:choose>

	</xsl:template>

	<xsl:template match="*|@*|comment()|processing-instruction()|text()">
		<xsl:copy>
			<xsl:apply-templates select="*|@*|comment()|processing-instruction()|text()" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="tns:subcategory[@name=$subcategoryName]">
		<xsl:copy>
			<xsl:apply-templates select="@*" />
			<xsl:apply-templates />
			<xsl:if test="string(../@name) = $categoryName">
				<xsl:text>
			</xsl:text>
				<xsl:element name="tns:product">
					<xsl:attribute name="name"><xsl:value-of
						select="$productName" /></xsl:attribute>
					<xsl:text>
				</xsl:text>
					<xsl:element name="tns:producer">
						<xsl:value-of select="$producer" />
					</xsl:element>
					<xsl:text>
				</xsl:text>
					<xsl:element name="tns:model">
						<xsl:value-of select="$model" />
					</xsl:element>
					<xsl:text>
				</xsl:text>
					<xsl:element name="tns:date-issue">
						<xsl:value-of select="$date" />
					</xsl:element>
					<xsl:text>
				</xsl:text>
					<xsl:element name="tns:color">
						<xsl:value-of select="$colour" />
					</xsl:element>
					<xsl:text>
				</xsl:text>
					<xsl:choose>
						<xsl:when test="$price != 0">
							<xsl:element name="tns:price">
								<xsl:value-of select="$price" />
							</xsl:element>
						</xsl:when>
						<xsl:otherwise>
							<xsl:element name="tns:miss" />
						</xsl:otherwise>
					</xsl:choose>
					<xsl:text>
			</xsl:text>
				</xsl:element>
				<xsl:text>
		</xsl:text>
			</xsl:if>
		</xsl:copy>
	</xsl:template>

	<xsl:template name="addProductFailed">
		<xsl:value-of
			select="class-for-messages:detectFailedMessages($validationUtil)" />
		<html>
			<head>
				<title>Add Product</title>
				<style type="text/css">
					table {
					display:inline-block;
					position: absolute;
					left: 50%;
					top: 50%;
					margin-left: -250px;
					margin-top: -150px;
					}

					.send {
					width:50px;
					}
					.errorMessage {
					color:red;
					}
				</style>
			</head>
			<body>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="save_product" />
					<input type="hidden" name="categoryName" value="{$categoryName}" />
					<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
					<table border="0" cellspacing="10">
						<tbody>
							<tr>
								<td>
									PRODUCT NAME
								</td>
								<td>
									<input type="text" name="productName" value="{$currentProductName}" />
								</td>
								<td class="errorMessage">
									<xsl:value-of
										select="class-for-messages:getNameMessage($validationUtil)" />
								</td>
							</tr>
							<tr>
								<td>
									PROVIDER
								</td>
								<td>
									<input type="text" name="provider" value="{$currentProvider}" />
								</td>
								<td class="errorMessage">
									<xsl:value-of
										select="class-for-messages:getProducerMessage($validationUtil)" />
								</td>
							</tr>
							<tr>
								<td>
									MODEL
								</td>
								<td>
									<input type="text" name="model" value="{$currentModel}" />
								</td>
								<td class="errorMessage">
									<xsl:value-of
										select="class-for-messages:getModelMessage($validationUtil)" />
								</td>
							</tr>
							<tr>
								<td>
									DATE OF ISSUE
								</td>
								<td>
									<input type="text" name="dateIssue" value="{$currentDateIssue}" />
								</td>
								<td class="errorMessage">
									<xsl:value-of
										select="class-for-messages:getDateMessage($validationUtil)" />
								</td>
							</tr>
							<tr>
								<td>
									COLOR
								</td>
								<td>
									<input type="text" name="colour" value="{$currentColor}" />
								</td>
								<td class="errorMessage">
									<xsl:value-of
										select="class-for-messages:getColorMessage($validationUtil)" />
								</td>
							</tr>
							<tr>
								<td>
									PRICE
								</td>
								<td>
									<input type="text" name="price" value="{$currentPrice}" />
								</td>
								<td class="errorMessage">
									<xsl:value-of
										select="class-for-messages:getPriceMessage($validationUtil)" />
								</td>
							</tr>
							<tr>
								<td>
									<input type="submit" value="save" class="send" />
								</td>
								<td colspan="2">
									<input type="button" class="send"
										onclick="location.href='Controller?command=product_list_page&amp;categoryName={$categoryName}&amp;subcategoryName={$subcategoryName}'"
										value="back" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>