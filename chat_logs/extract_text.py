import sys

CRITERIA_INDX      = 1
INP_FILE_INDX      = 2
OUT_FILE_INDX      = 3
WORDLIST_FILE_INDX = 4

class TextExtractor:
	
	EXCLUDE = 1
	INCLUDE = 2
	
	def __init__(self, criteria, input_file, output_file, WordsList):
		#open file for reading
		self.criteria    = criteria
		self.InpFileName = input_file 
		self.OutFileName = output_file
		self.WordsList   = WordsList
		
	def parseFile(self):
		print("[I]: Parsing [%s]" % (self.InpFileName))
		print("[I]: Search criteria - %s" % ("EXCLUDE" if self.criteria==self.EXCLUDE else "INCLUDE"))

		try:
			inp_file = open(self.InpFileName, "rt", encoding="utf8")
		except:
			print("[E]: Unable to open %s" % (self.InpFileName))
			exit(0)
			
		lines = inp_file.readlines()
		#print(lines)
		try:
			wr_file = open(self.OutFileName, "w", encoding="utf8")
		except:
			print("[E]: Unable to open %s" % (self.OutFileName))
			exit(0)

		wr_line_count = 0
		line_count    = 0
		for line in lines:
			include_flag = False
			exclude_flag = False
			line_count = line_count + 1
			for word in self.WordsList:
				if word in line:
					if self.criteria==self.EXCLUDE:
						exclude_flag = True
					elif self.criteria==self.INCLUDE:
						include_flag = True
					break					

			#print("inc = %s exc = %s" % (include_flag, exclude_flag))

			if self.criteria==self.EXCLUDE:
				if exclude_flag==False:
					wr_file.write("<%d> %s" % (line_count, line))
					wr_line_count = wr_line_count + 1
			elif self.criteria==self.INCLUDE:
				if include_flag==True:
					wr_file.write("<%d> %s" % (line_count, line))
					wr_line_count = wr_line_count + 1

		print("[I]: %d lines written to [%s]" % (wr_line_count, self.OutFileName))

		wr_file.close()
		inp_file.close()
		
		return(wr_line_count)

def GetWordList(filename, WordsList):
	try:
		f = open(filename, "r", encoding="utf8")
		lines = f.readlines()

		for line in lines:
			line = line.split('\n')
			WordsList.append(line[0])
	except:
		print("[E]: Unable to open %s" % (self.filename))
		
	return(WordsList)
		
if __name__=="__main__":
	if len(sys.argv)!=5:
		print("Usage: extract_text.py EXCLUDE/INCLUDE <input_file> <output_file> <wordlist_file>")
		print("EXCLUDE - EXCLUDES TEXT SPECIFIED IN <wordlist_file>")
		print("INCLUDE - INCLUDES TEXT SPECIFIED IN <wordlist_file>")
	else:
		WordsList = []
		GetWordList(sys.argv[WORDLIST_FILE_INDX], WordsList)
		#print(WordsList)

		if sys.argv[CRITERIA_INDX]=="EXCLUDE":
			TextExtractorHandle = TextExtractor(TextExtractor.EXCLUDE, sys.argv[INP_FILE_INDX], sys.argv[OUT_FILE_INDX], WordsList)
		else:
			TextExtractorHandle = TextExtractor(TextExtractor.INCLUDE, sys.argv[INP_FILE_INDX], sys.argv[OUT_FILE_INDX], WordsList)

		TextExtractorHandle.parseFile()
			