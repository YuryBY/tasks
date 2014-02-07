<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited by XMLSpy® -->
<xsl:stylesheet version="1.0" xmlns:tns="http://www.example.org/products"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:param name="categoryName" />

	<xsl:template match="/">
		<html>
			<body>
				<h2>Subcategories List</h2>
				<table border="0" cellspacing="10" style="padding-top: 30px; width: 100%">
					<tr>
						<th>Subcategory Name</th>
						<th>Quantity Of Products</th>
					</tr>
					<xsl:apply-templates select="tns:products" />
				</table>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="tns:products">
		<xsl:apply-templates select="tns:category" />
	</xsl:template>

	<xsl:template match="tns:category">
		<xsl:if test="@name=$categoryName">
			<xsl:apply-templates select="tns:subcategory" />
		</xsl:if>
	</xsl:template>

	<xsl:template match="tns:subcategory">
		<tr>
			<td style="text-align: center;">
				<a
					href="controller?command=to products&amp;categoryName={$categoryName}&amp;subcategoryName={@name}">
					<xsl:value-of select="@name" />
				</a>
			</td>
			<td style="text-align: center;">
				(
				<xsl:value-of select="count(tns:product)" />
				)
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>


