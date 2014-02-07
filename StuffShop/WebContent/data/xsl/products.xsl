<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited by XMLSpy® -->
<xsl:stylesheet version="1.0" xmlns:tns="http://www.example.org/products"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:param name="subcategoryName" />

	<xsl:param name="categoryName" />

	<xsl:template match="/">
		<table border="0" cellspacing="10" style="padding-top: 30px; width: 100%">
			<tr>
				<th>Name</th>
				<th>Producer</th>
				<th>Model</th>
				<th>Date-Issue</th>
				<th>Color</th>
				<th>Price</th>
			</tr>
			<xsl:apply-templates select="tns:products" />
		</table>
		<form class="addButton" name="createForm" action="controller">
			<input type="hidden" name="command" value="to add form" />
			<input type="hidden" name="depth" value="products" />
			<input type="hidden" name="categoryName" value="{$categoryName}" />
			<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
			<input type="submit" value="Add Goods" />
		</form>
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
		<xsl:if test="@name=$subcategoryName">
			<xsl:apply-templates select="tns:product" />
		</xsl:if>
	</xsl:template>

	<xsl:template match="tns:product">
		<tr>
			<td style="text-align: center;">
				<xsl:value-of select="@name" />
			</td>
			<td style="text-align: center;">
				<xsl:value-of select="tns:producer" />
			</td>
			<td style="text-align: center;">
				<xsl:value-of select="tns:model" />
			</td>
			<td style="text-align: center;">
				<xsl:value-of select="tns:date-issue" />
			</td>
			<td style="text-align: center;">
				<xsl:value-of select="tns:color" />
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="tns:price">
						<xsl:value-of select="tns:price" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="tns:miss" />
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>

</xsl:stylesheet>


