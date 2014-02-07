<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited by XMLSpy® -->
<xsl:stylesheet version="1.0" xmlns:tns="http://www.example.org/products"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<body>
				<h2>Categories List</h2>
				<table border="0" cellspacing="10" style="padding-top: 30px; width: 100%">
					<tr>
						<th>Category Name</th>
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
		<tr>
			<td style="text-align: center;">
				<a
					href="controller?command=to subcategories&amp;categoryName={@name}">
					<xsl:value-of select="@name" />
				</a>
			</td>
			<td style="text-align: center;">
				(
				<xsl:value-of select="count(tns:subcategory/tns:product)" />
				)
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>





