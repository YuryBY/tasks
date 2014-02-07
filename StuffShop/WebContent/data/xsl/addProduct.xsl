<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:tns="http://www.example.org/products"
	xmlns:vld="xalan://com.epam.xslapp.util.ProductValidator" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />

	<xsl:param name="subcategoryName" />
	<xsl:param name="categoryName" />
	<xsl:param name="productName" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="date" />
	<xsl:param name="color" />
	<xsl:param name="price" />
	<xsl:param name="miss" />

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="tns:subcategory">
		<xsl:choose>
			<xsl:when test="@name=$subcategoryName and ../@name=$categoryName">
				<xsl:copy>
					<xsl:apply-templates select="@* | node()" />
					<tns:product name="{$productName}">
						<xsl:text>		
				</xsl:text>
						<tns:producer>
							<xsl:value-of select="$producer" />
						</tns:producer>
						<xsl:text>		
				</xsl:text>
						<tns:model>
							<xsl:value-of select="$model" />
						</tns:model>
						<xsl:text>		
				</xsl:text>
						<tns:date-issue>
							<xsl:value-of select="$date" />
						</tns:date-issue>
						<xsl:text>		
				</xsl:text>
						<tns:color>
							<xsl:value-of select="$color" />
						</tns:color>
						<xsl:text>		
				</xsl:text>
						<xsl:if test="$price">
							<tns:price>
								<xsl:value-of select="$price" />
							</tns:price>
						</xsl:if>
						<xsl:if test="$miss">
							<tns:miss>
								<xsl:value-of select="$miss" />
							</tns:miss>
						</xsl:if>
						<xsl:text>			
			</xsl:text>
					</tns:product>
					<xsl:text>		
		</xsl:text>
				</xsl:copy>
			</xsl:when>
			<xsl:otherwise>
				<xsl:copy>
					<xsl:apply-templates select="@*|node()" />
				</xsl:copy>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>