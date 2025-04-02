from lxml import etree 

class LXmlParser:
    def __init__(self):
        self.InitStatus = True

    def parseXmlFile(self, FilePath):
        List = FilePath.split('\\')
        print("[I] Parsing %s" % List[len(List)-1] )

        try:
            self.tree = etree.parse(FilePath)
        except AttributeError as e:
            print(e)
            print("Unable to open/parse %s" % List[len(List)-1] )
            exit(0)

        self.root = self.tree.getroot()

    def getElementsByTag(self, TagName):
        ElementList = self.root.findall("./"+TagName)
        return(ElementList)
    
    def getElementsbyXPath(self, Xpath):
        ElementList = self.root.xpath(Xpath)
        #print(ElementList) 
        return(ElementList)
    
    def validateXMLDoc(self, XmlDocLink, XsdLink):
        XmlSchemaDoc = etree.parse(XsdLink)
        XmlSchema = etree.XMLSchema(XmlSchemaDoc)

        XmlDoc = etree.parse(XmlDocLink)
        return(XmlSchema.validate(XmlDoc))

if __name__ == '__main__':
    tree = etree.parse('C:\\Users\\veere\\Downloads\\sandbox\\JSAR\\4.3.1\\CanIf.epd')
    root = tree.getroot()

    ar_ver = root.xpath('/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/ADMIN-DATA/DOC-REVISIONS/DOC-REVISION/REVISION-LABEL')[0].text
    print('AUTOSAR VERSION: %s' % (ar_ver))

    # names = root.xpath('/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/CONTAINERS/ECUC-PARAM-CONF-CONTAINER-DEF/SHORT-NAME')
    # for name in names:
    #     print("Tag: %s Name:%s" % ('Cntr', name.text))

    sub_containers = root.xpath('/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/CONTAINERS/*')
    for sub_cntr in sub_containers:
        print('Tag: %s Name: %s' % ('SubCntr', sub_cntr.text))

    # refs = root.xpath('/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/CONTAINERS/ECUC-PARAM-CONF-CONTAINER-DEF/REFERENCES/ECUC-REFERENCE-DEF/SHORT-NAME')
    # for ref in refs:
    #     print(ref.text)

    # trace_item = root.xpath('/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/CONTAINERS/ECUC-PARAM-CONF-CONTAINER-DEF/RELATED-TRACE-ITEM-REF')
    # print(trace_item[0].text)

    CanIfParserHandle = LXmlParser()
    CanIfParserHandle.parseXmlFile('C:\\Users\\veere\\Downloads\\sandbox\\JSAR\\4.3.1\\CanIf.epd')
    #List = CanIfParserHandle.getElementsByTag('SHORT-NAME')
    List = CanIfParserHandle.getElementsbyXPath('/AUTOSAR/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/ADMIN-DATA/DOC-REVISIONS/DOC-REVISION/REVISION-LABEL')
    #List = CanIfParserHandle.getElementsbyXPath('./AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/CONTAINERS/ECUC-PARAM-CONF-CONTAINER-DEF')
    print(List)
    
    for elements in List:
        print(elements.text)
