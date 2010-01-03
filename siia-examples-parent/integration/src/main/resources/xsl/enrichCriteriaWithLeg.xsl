<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/legQuote">
        <legQuote>
            <flightQuote>
                <xsl:copy-of select="flightCriteria/node()"/>
                <xsl:copy-of select="leg/node()"/>
            </flightQuote>
            <hotelQuote>
                <xsl:copy-of select="hotelCriteria/node()"/>
                <xsl:copy-of select="leg/node()"/>
            </hotelQuote>
            <carQuote>
                <xsl:copy-of select="carCriteria/node()"/>
                <xsl:copy-of select="leg/node()"/>
            </carQuote>
        </legQuote>
    </xsl:template>
</xsl:stylesheet>