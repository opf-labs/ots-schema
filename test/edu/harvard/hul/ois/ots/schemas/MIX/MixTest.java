/* 
 * Copyright 2010 Harvard University Library
 * 
 * This file is part of OTS-Schemas.
 * 
 * OTS-Schemas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OTS-Schemas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with OTS-Schemas.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.harvard.hul.ois.ots.schemas.MIX;

import edu.harvard.hul.ois.ots.schemas.MIX.BasicDigitalObjectInformation;
import edu.harvard.hul.ois.ots.schemas.MIX.BasicImageCharacteristics;
import edu.harvard.hul.ois.ots.schemas.MIX.BasicImageInformation;
import edu.harvard.hul.ois.ots.schemas.MIX.BitsPerSample;
import edu.harvard.hul.ois.ots.schemas.MIX.ChangeHistory;
import edu.harvard.hul.ois.ots.schemas.MIX.CodecCompliance;
import edu.harvard.hul.ois.ots.schemas.MIX.ColorProfile;
import edu.harvard.hul.ois.ots.schemas.MIX.Compression;
import edu.harvard.hul.ois.ots.schemas.MIX.DigitalCameraCapture;
import edu.harvard.hul.ois.ots.schemas.MIX.Fixity;
import edu.harvard.hul.ois.ots.schemas.MIX.FormatDesignation;
import edu.harvard.hul.ois.ots.schemas.MIX.FormatRegistry;
import edu.harvard.hul.ois.ots.schemas.MIX.GeneralCaptureInformation;
import edu.harvard.hul.ois.ots.schemas.MIX.IccProfile;
import edu.harvard.hul.ois.ots.schemas.MIX.ImageAssessmentMetadata;
import edu.harvard.hul.ois.ots.schemas.MIX.ImageCaptureMetadata;
import edu.harvard.hul.ois.ots.schemas.MIX.ImageColorEncoding;
import edu.harvard.hul.ois.ots.schemas.MIX.ImageProcessing;
import edu.harvard.hul.ois.ots.schemas.MIX.JPEG2000;
import edu.harvard.hul.ois.ots.schemas.MIX.LocalProfile;
import edu.harvard.hul.ois.ots.schemas.MIX.Mix;
import edu.harvard.hul.ois.ots.schemas.MIX.ObjectIdentifier;
import edu.harvard.hul.ois.ots.schemas.MIX.PhotometricInterpretation;
import edu.harvard.hul.ois.ots.schemas.MIX.ScannerCapture;
import edu.harvard.hul.ois.ots.schemas.MIX.SourceInformation;
import edu.harvard.hul.ois.ots.schemas.MIX.SpatialMetrics;
import edu.harvard.hul.ois.ots.schemas.MIX.SpecialFormatCharacteristics;
import edu.harvard.hul.ois.ots.schemas.MIX.WhitePoint;
import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import org.custommonkey.xmlunit.*;


public class MixTest  extends XMLTestCase {
    
    
    /* Sample for testing BasicDigitalObjectInformation */
    private String bdoiSample = 
        "<mix:mix xmlns:mix=\"http://www.loc.gov/mix/v20\" \n" +
        "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >\n" + 
        "<mix:BasicDigitalObjectInformation> \n" +
        "  <mix:ObjectIdentifier> \n" +
        "    <mix:objectIdentifierType use=\"example\">type1</mix:objectIdentifierType> \n" +
        "    <mix:objectIdentifierValue>value1</mix:objectIdentifierValue> \n" +
        "  </mix:ObjectIdentifier> \n" +
        "  <mix:fileSize>144772</mix:fileSize> \n" +
        "  <mix:FormatDesignation> \n" +
        "    <mix:formatName>TIFF</mix:formatName>\n " +
        "    <mix:formatVersion>6.0</mix:formatVersion>\n " +
        "  </mix:FormatDesignation> \n" +
        "  <mix:FormatRegistry> \n" +
        "    <mix:formatRegistryName>GDFR</mix:formatRegistryName>\n " +
        "    <mix:formatRegistryKey>1234</mix:formatRegistryKey>\n " +
        "  </mix:FormatRegistry> \n" +
        "  <mix:byteOrder>big endian</mix:byteOrder> \n" +
        "  <mix:Compression> \n" +
        "    <mix:compressionScheme>hydraulic</mix:compressionScheme> \n" +
        "    <mix:compressionSchemeLocalList>http://x.y</mix:compressionSchemeLocalList> \n" +
        "    <mix:compressionSchemeLocalValue>xyz</mix:compressionSchemeLocalValue> \n" +
        "    <mix:compressionRatio> \n" +
        "      <mix:numerator>4</mix:numerator> \n" +
        "      <mix:denominator>1</mix:denominator> \n" +
        "    </mix:compressionRatio> \n" +
        "  </mix:Compression> \n" +
        "  <mix:Fixity> \n" +
        "    <mix:messageDigestAlgorithm>CRC32</mix:messageDigestAlgorithm> \n" +
        "  </mix:Fixity> \n" +
        "</mix:BasicDigitalObjectInformation> \n" +
        "</mix:mix>";

    /* This example contains a byteOrder element which violates the
     * enumerated type */
    private String errorSample = 
        "<mix:mix xmlns:mix=\"http://www.loc.gov/mix/v20\" \n" +
        "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >\n" + 
        "<mix:BasicDigitalObjectInformation> \n" +
        "  <mix:byteOrder>middle endian</mix:byteOrder> \n" +
        "</mix:BasicDigitalObjectInformation> \n" +
        "</mix:mix>";

    /* Sample for testing BasicImageInformation */
    private String biiSample = 
        "<mix:mix xmlns:mix=\"http://www.loc.gov/mix/v20\" \n" +
        "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >\n" + 
        "<mix:BasicImageInformation> \n" +
        "  <mix:BasicImageCharacteristics> \n" +
        "    <mix:imageWidth>2400</mix:imageWidth>\n" +
        "    <mix:imageHeight>1800</mix:imageHeight>\n" +
        "    <mix:PhotometricInterpretation> \n" +
        "      <mix:ColorProfile> \n" +
        "        <mix:IccProfile> \n" +
        "           <mix:iccProfileName>prof</mix:iccProfileName> \n" +
        "           <mix:iccProfileVersion>1.0</mix:iccProfileVersion> \n" +
        "           <mix:iccProfileURI>http://www.example.com</mix:iccProfileURI> \n" +
        "        </mix:IccProfile> \n" +
        "        <mix:LocalProfile> \n" +
        "          <mix:localProfileName>n1</mix:localProfileName> \n" +
        "          <mix:localProfileURL> http://www.example.com/local </mix:localProfileURL> \n" +
        "        </mix:LocalProfile> \n" +
        "        <mix:embeddedProfile>446A992</mix:embeddedProfile> \n" +
        "      </mix:ColorProfile> \n" +
        "    </mix:PhotometricInterpretation> \n" +
        "  </mix:BasicImageCharacteristics> \n" +
        "  <mix:SpecialFormatCharacteristics> \n" +
        "    <mix:JPEG2000> \n" +
        "      <mix:CodecCompliance> \n" +
        "        <mix:codec>xyz</mix:codec> \n" +
        "        <mix:codecVersion>1.0</mix:codecVersion> \n" +
        "        <mix:codestreamProfile>abcde</mix:codestreamProfile> \n" +
        "        <mix:complianceClass>abcde</mix:complianceClass> \n" +
        "      </mix:CodecCompliance> \n" +
        "      <mix:EncodingOptions> \n" +
        "        <mix:Tiles> \n" +
        "          <mix:tileWidth>1024</mix:tileWidth> \n" +
        "          <mix:tileHeight>2048</mix:tileHeight> \n" +
        "        </mix:Tiles> \n" +
        "        <mix:qualityLayers>4</mix:qualityLayers> \n" +
        "        <mix:resolutionLevels>2</mix:resolutionLevels> \n" +
        "      </mix:EncodingOptions> \n" +
        "    </mix:JPEG2000> \n" +
        "    <mix:Djvu use=\"test\"> \n" +
        "      <mix:djvuFormat>indirect</mix:djvuFormat> \n" +
        "    </mix:Djvu> \n" +
        "  </mix:SpecialFormatCharacteristics> \n" +
        "</mix:BasicImageInformation> \n" +
        "</mix:mix>";
    
    /* Sample for testing BasicImageInformation */
    private String biiSample2 = 
        "<mix:mix xmlns:mix=\"http://www.loc.gov/mix/v20\" \n" +
        "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >\n" + 
        "<mix:BasicImageInformation> \n" +
        "  <mix:BasicImageCharacteristics> \n" +
        "    <mix:imageWidth>2400</mix:imageWidth>\n" +
        "    <mix:imageHeight>1800</mix:imageHeight>\n" +
        "    <mix:PhotometricInterpretation> \n" +
        "<mix:colorSpace>RGB</mix:colorSpace> \n" +
		"<mix:ReferenceBlackWhite> \n" +
		"	<mix:Component> \n" +
		"		<mix:componentPhotometricInterpretation>R</mix:componentPhotometricInterpretation> \n" +
		"		<mix:footroom> \n" +
		"			<mix:numerator>25500</mix:numerator> \n" +
		"			<mix:denominator>100</mix:denominator> \n" +
		"		</mix:footroom> \n" +
		"		<mix:headroom> \n" +
		"			<mix:numerator>0</mix:numerator> \n" +
		"			<mix:denominator>100</mix:denominator> \n" +
		"		</mix:headroom> \n" +
		"	</mix:Component> \n" +
		"	<mix:Component> \n" +
		"		<mix:componentPhotometricInterpretation>G</mix:componentPhotometricInterpretation> \n" +
		"		<mix:footroom> \n" +
		"			<mix:numerator>25500</mix:numerator> \n" +
		"			<mix:denominator>100</mix:denominator> \n" +
		"		</mix:footroom> \n" +
		"		<mix:headroom> \n" +
		"			<mix:numerator>0</mix:numerator> \n" +
		"			<mix:denominator>100</mix:denominator> \n" +
		"		</mix:headroom> \n" +
		"	</mix:Component> \n" +
		"	<mix:Component> \n" +
		"		<mix:componentPhotometricInterpretation>B</mix:componentPhotometricInterpretation> \n" +
		"		<mix:footroom> \n" +
		"			<mix:numerator>25500</mix:numerator> \n" +
		"			<mix:denominator>100</mix:denominator> \n" +
		"		</mix:footroom> \n" +
		"		<mix:headroom> \n" +
		"			<mix:numerator>0</mix:numerator> \n" +
		"			<mix:denominator>100</mix:denominator> \n" +
		"		</mix:headroom> \n" +
		"	</mix:Component> \n" +
		"</mix:ReferenceBlackWhite> \n" +
        "    </mix:PhotometricInterpretation> \n" +
        "  </mix:BasicImageCharacteristics> \n" +
        "  <mix:SpecialFormatCharacteristics> \n" +
        "    <mix:JPEG2000> \n" +
        "      <mix:CodecCompliance> \n" +
        "        <mix:codec>xyz</mix:codec> \n" +
        "        <mix:codecVersion>1.0</mix:codecVersion> \n" +
        "        <mix:codestreamProfile>abcde</mix:codestreamProfile> \n" +
        "        <mix:complianceClass>abcde</mix:complianceClass> \n" +
        "      </mix:CodecCompliance> \n" +
        "      <mix:EncodingOptions> \n" +
        "        <mix:Tiles> \n" +
        "          <mix:tileWidth>1024</mix:tileWidth> \n" +
        "          <mix:tileHeight>2048</mix:tileHeight> \n" +
        "        </mix:Tiles> \n" +
        "        <mix:qualityLayers>4</mix:qualityLayers> \n" +
        "        <mix:resolutionLevels>2</mix:resolutionLevels> \n" +
        "      </mix:EncodingOptions> \n" +
        "    </mix:JPEG2000> \n" +
        "    <mix:Djvu use=\"test\"> \n" +
        "      <mix:djvuFormat>indirect</mix:djvuFormat> \n" +
        "    </mix:Djvu> \n" +
        "  </mix:SpecialFormatCharacteristics> \n" +
        "</mix:BasicImageInformation> \n" +
        "</mix:mix>";

    /* Sample for testing ImageCaptureMetadata */
    private String icmSample = 
        "<mix:mix xmlns:mix=\"http://www.loc.gov/mix/v20\"> \n" +
        "<mix:ImageCaptureMetadata> \n" +
        "  <mix:SourceInformation> \n" +
        "  </mix:SourceInformation> \n" +
        "  <mix:GeneralCaptureInformation> \n" +
        "  </mix:GeneralCaptureInformation> \n" +
        "  <mix:ScannerCapture> \n" +
        "    <mix:ScannerModel> \n" +
        "      <mix:scannerModelName>TOPAZ iX Scanner</mix:scannerModelName> \n" +
        "    </mix:ScannerModel> \n" +
        "  </mix:ScannerCapture> \n" +
        "  <mix:DigitalCameraCapture> \n" +
        "    <mix:digitalCameraManufacturer>Nikon</mix:digitalCameraManufacturer> \n" +
        "    <mix:DigitalCameraModel> \n" +
        "      <mix:digitalCameraModelName>Supercamera</mix:digitalCameraModelName> \n" +
        "      <mix:digitalCameraModelNumber>100</mix:digitalCameraModelNumber> \n" +
        "      <mix:digitalCameraModelSerialNo>999999</mix:digitalCameraModelSerialNo> \n" +
        "    </mix:DigitalCameraModel> \n" +
        "    <mix:cameraSensor>TwoChipColorArea</mix:cameraSensor> \n" +
        "    <mix:CameraCaptureSettings> \n" +
        "      <mix:ImageData> \n" +
        "      </mix:ImageData> \n" +
        "      <mix:GPSData> \n" +
        "      </mix:GPSData> \n" +
        "    </mix:CameraCaptureSettings> \n" +
        "  </mix:DigitalCameraCapture> \n" +
        "  <mix:orientation> unknown </mix:orientation> \n" +
        "  <mix:methodology>ad hoc</mix:methodology> \n" +
        "</mix:ImageCaptureMetadata> \n" +
        "</mix:mix>";
    
    /** Sample for testing ImageAssessmentMetadata */
    private String iamSample =

        "<mix:mix xmlns:mix=\"http://www.loc.gov/mix/v20\"> \n" +
        "<mix:ImageAssessmentMetadata> \n" +
        "  <mix:SpatialMetrics> \n" +
        "    <mix:samplingFrequencyPlane>object plane</mix:samplingFrequencyPlane> \n" +
        "    <mix:samplingFrequencyUnit>in.</mix:samplingFrequencyUnit> \n" +
        "    <mix:xSamplingFrequency><mix:numerator>1</mix:numerator><mix:denominator>100</mix:denominator></mix:xSamplingFrequency> \n" +
        "    <mix:ySamplingFrequency><mix:numerator>1</mix:numerator><mix:denominator>300</mix:denominator></mix:ySamplingFrequency> \n" +
        "  </mix:SpatialMetrics> \n" +
        "  <mix:ImageColorEncoding> \n" +
        "    <mix:BitsPerSample> \n" +
        "      <mix:bitsPerSampleValue>8</mix:bitsPerSampleValue> \n" +
        "      <mix:bitsPerSampleUnit>integer</mix:bitsPerSampleUnit> \n" +
        "    </mix:BitsPerSample> \n" +
        "    <mix:samplesPerPixel>3</mix:samplesPerPixel> \n" +
        "    <mix:extraSamples>range or depth data</mix:extraSamples>\n" +
        "    <mix:Colormap> \n" +
        "      <mix:colormapReference>http://sample.sample</mix:colormapReference> \n" +
        "      <mix:embeddedColormap>123456</mix:embeddedColormap> \n" +
        "    </mix:Colormap> \n" +
        "    <mix:grayResponseCurve>12</mix:grayResponseCurve> \n" +
        "    <mix:grayResponseCurve>22</mix:grayResponseCurve> \n" +
        "    <mix:grayResponseCurve>32</mix:grayResponseCurve> \n" +
        "    <mix:grayResponseCurve>42</mix:grayResponseCurve> \n" +
        "    <mix:grayResponseUnit>Number represents thousandths of a unit</mix:grayResponseUnit> \n" +
        "    <mix:WhitePoint> \n" +
        "      <mix:whitePointXValue> " +
        "       <numerator>1</numerator><denominator>4</denominator> " +
        "      </mix:whitePointXValue> \n" +
        "      <mix:whitePointYValue> " +
        "       <numerator>2</numerator><denominator>6</denominator> " +
        "      </mix:whitePointYValue> \n" +
        "    </mix:WhitePoint> \n" +
        "    <mix:WhitePoint> \n" +
        "      <mix:whitePointXValue> " +
        "       <numerator>5</numerator><denominator>6</denominator> " +
        "      </mix:whitePointXValue> \n" +
        "      <mix:whitePointYValue> " +
        "       <numerator>7</numerator><denominator>8</denominator> " +
        "      </mix:whitePointYValue> \n" +
        "    </mix:WhitePoint> \n" +
        "    <mix:PrimaryChromaticities> \n" +
        "      <mix:primaryChromaticitiesRedX> " +
        "       <numerator>7</numerator><denominator>8</denominator> " +
        "      </mix:primaryChromaticitiesRedX> " +
        "      <mix:primaryChromaticitiesRedY> " +
        "       <numerator>7</numerator><denominator>9</denominator> " +
        "      </mix:primaryChromaticitiesRedY> " +
        "      <mix:primaryChromaticitiesGreenX> " +
        "       <numerator>7</numerator><denominator>10</denominator> " +
        "      </mix:primaryChromaticitiesGreenX> " +
        "      <mix:primaryChromaticitiesGreenY> " +
        "       <numerator>7</numerator><denominator>11</denominator> " +
        "      </mix:primaryChromaticitiesGreenY> " +
        "      <mix:primaryChromaticitiesBlueX> " +
        "       <numerator>7</numerator><denominator>12</denominator> " +
        "      </mix:primaryChromaticitiesBlueX> " +
        "      <mix:primaryChromaticitiesBlueY> " +
        "       <numerator>7</numerator><denominator>13</denominator> " +
        "      </mix:primaryChromaticitiesBlueY> " +
        "    </mix:PrimaryChromaticities> \n" +
        "  </mix:ImageColorEncoding> \n" +
        "  <mix:TargetData> \n" +
        "  </mix:TargetData> \n" +
        "</mix:ImageAssessmentMetadata> \n" +
        "</mix:mix>";
        
    /** Sample for testing ChangeHistory */
    private String chSample =

        "<mix:mix xmlns:mix=\"http://www.loc.gov/mix/v20\"> \n" +
        "<mix:ChangeHistory> \n" +
        "  <mix:ImageProcessing> \n" +
        "    <mix:dateTimeProcessed>2009-04-01</mix:dateTimeProcessed> \n" +
        "    <mix:sourceData>abcde</mix:sourceData> \n" +
        "    <mix:processingAgency>cdefg</mix:processingAgency> \n" +
        "    <mix:processingRationale>cdefg</mix:processingRationale> \n" +
        "    <mix:ProcessingSoftware> \n" +
        "      <mix:processingSoftwareName>JHOVE</mix:processingSoftwareName> \n" +
        "      <mix:processingSoftwareVersion>1.4</mix:processingSoftwareVersion> \n" +
        "      <mix:processingOperatingSystemName>Unix</mix:processingOperatingSystemName> \n" +
        "      <mix:processingOperatingSystemVersion>System 7</mix:processingOperatingSystemVersion> \n" +
        "    </mix:ProcessingSoftware> \n" +
        "    <mix:ProcessingSoftware> \n" +
        "      <mix:processingSoftwareName>FITS</mix:processingSoftwareName> \n" +
        "      <mix:processingSoftwareVersion>2.0</mix:processingSoftwareVersion> \n" +
        "      <mix:processingOperatingSystemName>Unix</mix:processingOperatingSystemName> \n" +
        "      <mix:processingOperatingSystemVersion>System 7</mix:processingOperatingSystemVersion> \n" +
        "    </mix:ProcessingSoftware> \n" +
        "    <mix:processingActions>mangle</mix:processingActions> \n" +
        "    <mix:processingActions>mulch</mix:processingActions> \n" +
        "  </mix:ImageProcessing> \n" +
        "  <mix:ImageProcessing> \n" +
        "    <mix:dateTimeProcessed>2009-05-02</mix:dateTimeProcessed> \n" +
        "    <mix:sourceData>abcde</mix:sourceData> \n" +
        "  </mix:ImageProcessing> \n" +
        "</mix:ChangeHistory> \n" +
        "</mix:mix>";
        
    public void testBDOI () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(bdoiSample));
        //move to root tag
        xmlr.nextTag();
        Mix mix = new Mix (xmlr);
        BasicDigitalObjectInformation bdoi = mix.getBasicDigitalObjectInformation ();
        assertNotNull (bdoi);
        ObjectIdentifier oi = bdoi.getObjectIdentifier ();
        assertNotNull (oi);
        assertTrue ("type1".equals (oi.getObjectIdentifierType().toString()));
        assertTrue ("value1".equals (oi.getObjectIdentifierValue().toString()));
        assertEquals ((Integer) 144772, bdoi.getFileSize ().toInteger() );
        
        FormatDesignation fd = bdoi.getFormatDesignation ();
        assertNotNull (fd);
        assertTrue ("TIFF".equals (fd.getFormatName ().toString()));
        assertTrue ("6.0".equals (fd.getFormatVersion ().toString()));
        
        FormatRegistry fr = bdoi.getFormatRegistry ();
        assertNotNull (fr);
        assertTrue ("GDFR".equals (fr.getFormatRegistryName().toString()));
        assertTrue ("1234".equals (fr.getFormatRegistryKey().toString()));
        
        assertTrue ("big endian".equals (bdoi.getByteOrder ().toString()));
        
        Compression cmp = bdoi.getCompression();
        assertNotNull (cmp);
        assertTrue ("hydraulic".equals(cmp.getCompressionScheme().toString()));
        assertTrue ("http://x.y".equals(cmp.getCompressionSchemeLocalList().toString()));
        assertTrue ("xyz".equals (cmp.getCompressionSchemeLocalValue().toString()));
        assertEquals (new Rational (4, 1), cmp.getCompressionRatio().toRational());
        
        List<Fixity> fixityList = bdoi.getFixities ();
        assertNotNull (fixityList);
        assertEquals(1, fixityList.size());
        Fixity f = (Fixity) fixityList.get(0);
        assertEquals ("Fixity", f.getName ());
        String mda = f.getMessageDigestAlgorithm().toString();
        assertTrue ("CRC32".equals (mda));
    }

    public void testBII () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(biiSample));
        //move to root tag
        xmlr.nextTag();
        Mix mix = new Mix (xmlr);
        assertEquals ("mix", mix.getName ());
        BasicImageInformation bii = mix.getBasicImageInformation ();
        assertEquals ("BasicImageInformation", bii.getName ());
        BasicImageCharacteristics bic = bii.getBasicImageCharacteristics();
        assertEquals ("BasicImageCharacteristics", bic.getName ());
        IntegerElement ielem = bic.getImageWidth ();
        assertEquals ("imageWidth", ielem.getName ());
        assertEquals ((Integer) 2400, ielem.toInteger ());
        ielem = bic.getImageHeight ();
        assertEquals ("imageHeight", ielem.getName ());
        assertEquals ((Integer) 1800, ielem.toInteger());
        PhotometricInterpretation pi = bic.getPhotometricInterpretation();
        assertNotNull (pi);
        assertNull (pi.getColorSpace ());
        ColorProfile cp = pi.getColorProfile ();
        assertEquals ("ColorProfile", cp.getName ());
        IccProfile iccp = cp.getIccProfile();
        assertEquals ("IccProfile", iccp.getName ());
        assertTrue ("prof".equals (iccp.getIccProfileName().toString()));
        assertTrue ("1.0".equals (iccp.getIccProfileVersion().toString()));
        assertTrue ("http://www.example.com".equals (iccp.getIccProfileURI().toString()));
        LocalProfile lp = cp.getLocalProfile ();
        assertEquals ("LocalProfile", lp.getName ());
        StringElement lpn = lp.getLocalProfileName ();
        assertEquals ("localProfileName", lpn.getName ());
        assertTrue ("n1".equals(lpn.toString()));
        assertTrue ("http://www.example.com/local".equals (lp.getLocalProfileURL().toString().trim()));
        StringElement ep = cp.getEmbeddedProfile();
        assertEquals ("embeddedProfile", ep.getName ());
        
        SpecialFormatCharacteristics sf = bii.getSpecialFormatCharacteristics();
        assertEquals ("SpecialFormatCharacteristics", sf.getName ());
        assertNull (sf.getMrSID());
        assertNotNull (sf.getDjvu());
        JPEG2000 jp2 = sf.getJPEG2000();
        assertEquals ("JPEG2000", jp2.getName ());
        CodecCompliance cc = jp2.getCodecCompliance();
        assertEquals ("CodecCompliance", cc.getName ());
        assertTrue ("xyz".equals (cc.getCodec().toString()));
        assertTrue ("1.0".equals (cc.getCodecVersion().toString()));
        assertTrue ("abcde".equals (cc.getCodestreamProfile().toString()));
        assertTrue ("abcde".equals (cc.getComplianceClass().toString()));
    }
    
    @SuppressWarnings("unused")
	public void testBII2 () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(biiSample2));
        //move to root tag
        xmlr.nextTag();
        Mix mix = new Mix (xmlr);
        assertEquals ("mix", mix.getName ());
        BasicImageInformation bii = mix.getBasicImageInformation ();
        assertEquals ("BasicImageInformation", bii.getName ());
        BasicImageCharacteristics bic = bii.getBasicImageCharacteristics();
        assertEquals ("BasicImageCharacteristics", bic.getName ());
        IntegerElement ielem = bic.getImageWidth ();
        assertEquals ("imageWidth", ielem.getName ());
        assertEquals ((Integer) 2400, ielem.toInteger ());
        ielem = bic.getImageHeight ();
        assertEquals ("imageHeight", ielem.getName ());
        assertEquals ((Integer) 1800, ielem.toInteger());
        PhotometricInterpretation pi = bic.getPhotometricInterpretation();
        assertNotNull (pi);
        
        ReferenceBlackWhite rbw = pi.getReferenceBlackWhites().get(0);
        Component comp = rbw.getComponents().get(0);
        
        
        SpecialFormatCharacteristics sf = bii.getSpecialFormatCharacteristics();
        assertEquals ("SpecialFormatCharacteristics", sf.getName ());
        assertNull (sf.getMrSID());
        assertNotNull (sf.getDjvu());
        JPEG2000 jp2 = sf.getJPEG2000();
        assertEquals ("JPEG2000", jp2.getName ());
        CodecCompliance cc = jp2.getCodecCompliance();
        assertEquals ("CodecCompliance", cc.getName ());
        assertTrue ("xyz".equals (cc.getCodec().toString()));
        assertTrue ("1.0".equals (cc.getCodecVersion().toString()));
        assertTrue ("abcde".equals (cc.getCodestreamProfile().toString()));
        assertTrue ("abcde".equals (cc.getComplianceClass().toString()));
    }
    
    public void testICM () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(icmSample));
        //move to root tag
        xmlr.nextTag();
        Mix mix = new Mix (xmlr);
        assertNull (mix.getBasicDigitalObjectInformation());
        ImageCaptureMetadata icm = mix.getImageCaptureMetadata ();
        assertEquals ("ImageCaptureMetadata", icm.getName ());
        SourceInformation si = icm.getSourceInformation ();
        assertEquals ("SourceInformation", si.getName ());
        GeneralCaptureInformation gci = icm.getGeneralCaptureInformation ();
        assertEquals ("GeneralCaptureInformation", gci.getName ());
        ScannerCapture sc = icm.getScannerCapture();
        assertEquals ("ScannerCapture", sc.getName ());
        ScannerModel sm = sc.getScannerModel();
        StringElement smname = sm.getScannerModelName();
        assertEquals ("TOPAZ iX Scanner", smname.toString());
        DigitalCameraCapture dcc = icm.getDigitalCameraCapture();
        assertEquals ("DigitalCameraCapture", dcc.getName ());
        icm.getOrientation().toString();
        icm.getMethodology().toString ();
    }
    
    @SuppressWarnings("unused")
	public void testIAM () throws Exception  {
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(iamSample));
        //move to root tag
        xmlr.nextTag();
        Mix mix = new Mix (xmlr);
        ImageAssessmentMetadata iam = mix.getImageAssessmentMetadata ();
        assertNotNull (iam);
        assertEquals (iam, iam);   // and that's all iam
        SpatialMetrics sm = iam.getSpatialMetrics ();
        assertEquals ("SpatialMetrics", sm.getName ());
        assertEquals ("object plane", sm.getSamplingFrequencyPlane().toString());
        assertEquals ("in.", sm.getSamplingFrequencyUnit().toString());
        Rational rat = sm.getXSamplingFrequency().toRational();
        assertTrue (rat.getNumerator() == 1);
        assertTrue (rat.getDenominator() == 100);
        rat = sm.getYSamplingFrequency().toRational();
        assertTrue (rat.getNumerator() == 1);
        assertTrue (rat.getDenominator() == 300);
        ImageColorEncoding ice = iam.getImageColorEncoding ();
        assertEquals ("ImageColorEncoding", ice.getName ());
        BitsPerSample bps = ice.getBitsPerSample ();
        assertEquals ("BitsPerSample", bps.getName ());
        iam.getTargetData ();
        
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory f = XMLOutputFactory.newInstance();
		XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter);
		mix.setRoot(true);
		mix.output(sw);
		sw.close();
	    String newXml = stringWriter.toString();
	    stringWriter.close();
        
        
    }
    
    public void testCH () throws Exception {
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(chSample));
        //move to root tag
        xmlr.nextTag();
        Mix mix = new Mix (xmlr);
        ChangeHistory ch = mix.getChangeHistory ();
        assertNotNull (ch);
        List<ImageProcessing> imps = ch.getImageProcessings ();
        assertNotNull (imps);
        assertTrue (imps.size() == 2);
        ImageProcessing imp = (ImageProcessing) imps.get(0);
        assertEquals ("ImageProcessing", imp.getName ());
        imp = (ImageProcessing) imps.get (1);
        assertEquals ("ImageProcessing", imp.getName ());
    }
    
    /** Check for error catching */
    public void testExceptionCatch () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(errorSample));
        //move to root tag
        xmlr.nextTag();
        boolean exceptionSeen = false;
        try {
            new Mix (xmlr);
        }
        catch (XmlContentException e) {
            exceptionSeen = true;
        }
        assertTrue (exceptionSeen);
        
    }

    /** A test for composing a Mix object. This can be fairly simple, with tests
     *  for composing each subelement associated with their respective classes. */
    public void testComposition () throws Exception {
        Mix mix = new Mix ();
        BasicDigitalObjectInformation bdoi = new BasicDigitalObjectInformation();
        mix.setBasicDigitalObjectInformation (bdoi);
        ObjectIdentifier oi = new ObjectIdentifier ();
        bdoi.setObjectIdentifier (oi);
        oi.setObjectIdentifierType ("type1a");
        oi.setObjectIdentifierValue ("value1a");
        oi.setChildAttribute ("objectIdentifierType", "use", "sample");
        BasicImageInformation bii = new BasicImageInformation ();
        mix.setBasicImageInformation(bii);
        
        // Now pull it back out
        bdoi = mix.getBasicDigitalObjectInformation ();
        assertEquals ("BasicDigitalObjectInformation", bdoi.getName ());
        oi = bdoi.getObjectIdentifier ();
        assertNotNull (oi);
        assertTrue ("type1a".equals(oi.getObjectIdentifierType().toString()));
        assertTrue ("sample".equals (oi.getChildAttribute ("objectIdentifierType", "use")));
        assertTrue ("value1a".equals(oi.getObjectIdentifierValue().toString()));
        bii = mix.getBasicImageInformation();
        assertNotNull (bii);
    }
    
    /** Test creating by composition and writing to XML. */
    public void testWrite () throws Exception {
        // Create a mix object
        Mix mix = new Mix ();
        BasicDigitalObjectInformation bdoi = new BasicDigitalObjectInformation();
        mix.setBasicDigitalObjectInformation (bdoi);
        ObjectIdentifier oi = new ObjectIdentifier ();
        bdoi.setObjectIdentifier (oi);
        oi.setObjectIdentifierType ("type1a");
        oi.setObjectIdentifierValue ("value1a");
        bdoi.setFileSize(24000);
        
        BasicImageInformation bii = new BasicImageInformation ();
        mix.setBasicImageInformation(bii);
        
        ImageAssessmentMetadata iam = new ImageAssessmentMetadata();
        mix.setImageAssessmentMetadata(iam);
        ImageColorEncoding ice = new ImageColorEncoding();
        iam.setImageColorEncoding(ice);
        BitsPerSample bps = new BitsPerSample();
        bps.addBitsPerSampleValue (1);
        ice.setBitsPerSample(bps);
        ice.setSamplesPerPixel(3);
        ice.addExtraSamples("associated alpha data (with pre-multiplied color)");
        ice.addGrayResponseCurve(10);
        ice.addGrayResponseCurve(20);
        ice.addGrayResponseCurve(30);
        WhitePoint wp = new WhitePoint ();
        ice.addWhitePoint(wp);
        Rational r = new Rational (1, 2);
        wp.setWhitePointXValue(r);
        r = new Rational (2, 3);
        wp.setWhitePointYValue(r);
        // Now write it out
        XMLOutputFactory f = XMLOutputFactory.newInstance();
        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
        mix.output(sw);
        sw.close();
        String newXml = stringWriter.toString();
        stringWriter.close();
        System.out.println (newXml);
    }


    /** This test is specifically to check the output of unprocessed XML. */
    public void testWriteAnyXml () throws Exception {
        
        // Read the BasicImageInformation test, which contains a Djvu
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(biiSample));
        //move to root tag
        xmlr.nextTag();
        Mix mix = new Mix (xmlr);

        // Now write it out
        XMLOutputFactory f = XMLOutputFactory.newInstance();
        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
        mix.output(sw);
        sw.close();
        String newXml = stringWriter.toString();
        stringWriter.close();
        System.out.println (newXml);

        // Now try something similar with bdoiSample
        xmlif = XMLInputFactory.newInstance();
        xmlr = xmlif.createXMLStreamReader(new StringReader(bdoiSample));
        //move to root tag
        xmlr.nextTag();
        mix = new Mix (xmlr);

        // Now write it out
        f = XMLOutputFactory.newInstance();
        stringWriter = new StringWriter();
        sw = f.createXMLStreamWriter(stringWriter); 
        mix.output(sw);
        sw.close();
        newXml = stringWriter.toString();
        stringWriter.close();
        System.out.println (newXml);

    }
}
