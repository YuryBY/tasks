<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:tns="http://www.example.org/products"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />

	<xsl:param name="subcategoryName" />
	<xsl:param name="categoryName" />
	<xsl:param name="productName" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="date" />
	<xsl:param name="color" />
	<xsl:param name="price" />
	<xsl:param name="miss" />

	<xsl:param name="wrongProductName" />
	<xsl:param name="wrongProducer" />
	<xsl:param name="wrongModel" />
	<xsl:param name="wrongDateIssue" />
	<xsl:param name="wrongColor" />
	<xsl:param name="wrongPrice" />

	<xsl:template match="/">
		<form class="addForm" action="controller?command=save product"
			method="post">
			<table>
				<tr>
					<td>
						Name:
						<input type="text" name="name" value="{$productName}" />
					</td>
					<td>
						<div class="error">
							<xsl:value-of select="$wrongProductName" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						Producer:
						<input type="text" name="producer" value="{$producer}" />
					</td>
					<td>
						<div class="error">
							<xsl:value-of select="$wrongProducer" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						Model:
						<input type="text" name="model" value="{$model}" />
					</td>
					<td>
						<div class="error">
							<xsl:value-of select="$wrongModel" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						Date-Issue:
						<input type="text" name="date" value="{$date}" />
					</td>
					<td>
						<div class="error">
							<xsl:value-of select="$wrongDateIssue" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						Color:
						<input type="text" name="color" value="{$color}" />
					</td>
					<td>
						<div class="error">
							<xsl:value-of select="$wrongColor" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						Price:
						<xsl:choose>
							<xsl:when test="$price">
								<input type="text" name="price" value="{$price}" />
							</xsl:when>
							<xsl:when test="$miss">
								<input type="text" name="price" value="{$miss}" />
							</xsl:when>
							<xsl:otherwise>
								<input type="text" name="price" />
							</xsl:otherwise>
						</xsl:choose>
					</td>
					<td>
						<div class="error">
							<xsl:value-of select="$wrongPrice" />
						</div>
					</td>
				</tr>
			</table>

			<input type="hidden" name="categoryName" value="{$categoryName}" />
			<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
			<input type="submit" value="Save" />
			<input type="button" class="send"
				onclick="location.href='controller?command=cancel&amp;categoryName={$categoryName}&amp;subcategoryName={$subcategoryName}'"
				value="Cancel" />
			<br />
			<br />
		</form>
	</xsl:template>


</xsl:stylesheet>


