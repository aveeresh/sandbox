import xml.etree.ElementTree as ET

class XMLParser:
    def __init__(self):
        self.InitStatus = True

    def parseXml(self, FileName):
        try:
            self.tree = ET.parse(FileName, 'xml')
        except:
            List = FileName.split('\\')
            print("Unable to open/parse %s" % List[len(List)-1] )
            exit(0)
        self.root = self.tree.getroot()

    def getElementsByTag(self, TagName):
        ElementList = self.root.findall("./"+TagName)
        return(ElementList)
    
    def getElementsbyXPath(self, Xpath):
        
    
    def validateXMLDoc(self, XmlDocLink, XsdLink):
        XmlSchemaDoc = ET.parse(XsdLink)
        XmlSchema = ET.XMLSchema(XmlSchemaDoc)

        XmlDoc = ET.parse(XmlDocLink)
        return(XmlSchema.validate(XmlDoc))

if __name__ == '__main__':
    tree = ET.parse('C:\\Users\\veere\\Downloads\\sandbox\\JSAR\\4.3.1\\CanIf.epd')
    root = tree.getroot()

    list = root.findall('./SHORT-NAME')
    print(list)

    CanIfXmlHandle = XMLParser()
    CanIfXmlHandle.parseXml('C:\\Users\\veere\\Downloads\\sandbox\\JSAR\\4.3.1\\CanIf.epd')
    List = CanIfXmlHandle.getElementsByTag('SHORT-NAME')
    print(List)
    
    for elements in List:
        print(elements)