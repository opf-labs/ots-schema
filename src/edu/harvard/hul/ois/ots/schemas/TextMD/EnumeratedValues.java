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
package edu.harvard.hul.ois.ots.schemas.TextMD;

import java.util.Arrays;
import java.util.List;

public class EnumeratedValues {
	
	public static final List<String> LINEBREAK = Arrays.asList("CR","CR/LF","LF");

	public static final List<String> ROLE = Arrays.asList("OCR","TRANSCRIBER","MARKUP","EDITOR");
	
	public static final List<String> CHARSET = Arrays.asList(
			"ANSI_X3.4-1968","ISO-10646-UTF-1","ISO_646.basic:1983","INVARIANT","ISO_646.irv:1983",
			"BS_4730","NATS-SEFI","NATS-SEFI-ADD","NATS-DANO","NATS-DANO-ADD","SEN_850200_B",
			"SEN_850200_C","KS_C_5601-1987","ISO-2022-KR","EUC-KR","ISO-2022-JP","ISO-2022-JP-2",
			"ISO-2022-CN","ISO-2022-CN-EXT","JIS_C6220-1969-jp","JIS_C6220-1969-ro","IT","PT","ES",
			"greek7-old","latin-greek","DIN_66003","NF_Z_62-010_(1973)","latin-greek-1","ISO_5427",
			"JIS_C6226-1978","BS_viewdata","INIS","INIS-8","INIS-cyrillic","ISO_5427:1981",
			"ISO_5428:1980","GB_1988-80","GB_2312-80","NS_4551-1","NS_4551-2","NF_Z_62-010",
			"videotex-suppl","PT2","ES2","MSZ_7795.3","JIS_C6226-1983","greek7","ASMO_449",
			"iso-ir-90","JIS_C6229-1984-a","JIS_C6229-1984-b","JIS_C6229-1984-b-add",
			"JIS_C6229-1984-hand","JIS_C6229-1984-hand-add","JIS_C6229-1984-kana","ISO_2033-1983",
			"ANSI_X3.110-1983","ISO_8859-1:1987","ISO_8859-2:1987","ISO_8859-3:1988","ISO_8859-4:1988",
			"T.61-7bit","ECMA-cyrillic","CSA_Z243.4-1985-1","CSA_Z243.4-1985-2","CSA_Z243.4-1985-gr",
			"ISO_8859-6:1987","ISO_8859-6-E","ISO_8859-6-I","ISO_8859-7:1987","T.101-G2",
			"ISO_8859-8:1988","ISO_8859-8-E","ISO_8859-8-I","CSN_369103","JUS_I.B1.002","ISO_6937-2-add",
			"IEC_P27-1","ISO_8859-5:1988","JUS_I.B1.003-serb","JUS_I.B1.003-mac","ISO_8859-9:1989",
			"greek-ccitt","NC_NC00-10:81","ISO_6937-2-25","GOST_19768-74","ISO_8859-supp",
			"ISO_10367-box","ISO-8859-10","latin-lap","JIS_X0212-1990","DS_2089","us-dk","dk-us",
			"JIS_X0201","KSC5636","ISO-10646-UCS-2","ISO-10646-UCS-4","DEC-MCS","hp-roman8","macintosh",
			"IBM037","IBM038","IBM273","IBM274","IBM275","IBM277","IBM278","IBM280","IBM281","IBM284",
			"IBM285","IBM290","IBM297","IBM420","IBM423","IBM424","IBM437","IBM500","IBM775","IBM850",
			"IBM851","IBM852","IBM855","IBM857","IBM860","IBM861","IBM862","IBM863","IBM864","IBM865",
			"IBM866","IBM869","IBM870","IBM871","IBM880","IBM891","IBM903","IBM904","IBM905","IBM918",
			"IBM1026","EBCDIC-AT-DE","EBCDIC-AT-DE-A","EBCDIC-CA-FR","EBCDIC-DK-NO","EBCDIC-DK-NO-A",
			"EBCDIC-FI-SE","EBCDIC-FI-SE-A","EBCDIC-FR","EBCDIC-IT","EBCDIC-PT","EBCDIC-ES","EBCDIC-ES-A",
			"EBCDIC-ES-S","EBCDIC-UK","EBCDIC-US","UNKNOWN-8BIT","MNEMONIC","MNEM","VISCII","VIQR",
			"KOI8-R","KOI8-U","IBM00858","IBM00924","IBM01140","IBM01141","IBM01142","IBM01143",
			"IBM01144","IBM01145","IBM01146","IBM01147","IBM01148","IBM01149","Big5-HKSCS","UNICODE-1-1",
			"SCSU","UTF-7","UTF-16BE","UTF-16LE","UTF-16","CESU-8","UTF-32","UTF-32BE","UTF-32LE",
			"UNICODE-1-1-UTF-7","UTF-8","ISO-8859-13","ISO-8859-14","ISO-8859-15","ISO-8859-16",
			"JIS_Encoding","Shift_JIS","Extended_UNIX_Code_Packed_Format_for_Japanese",
			"Extended_UNIX_Code_Fixed_Width_for_Japanese","ISO-10646-UCS-Basic",
			"ISO-10646-Unicode-Latin1","ISO-10646-J-1","ISO-Unicode-IBM-1268","ISO-Unicode-IBM-1276",
			"ISO-Unicode-IBM-1264","ISO-Unicode-IBM-1265","ISO-8859-1-Windows-3.0-Latin-1",
			"ISO-8859-1-Windows-3.1-Latin-1","ISO-8859-2-Windows-Latin-2","ISO-8859-9-Windows-Latin-5",
			"Adobe-Standard-Encoding","Ventura-US","Ventura-International","PC8-Danish-Norwegian",
			"PC8-Turkish","IBM-Symbols","IBM-Thai","HP-Legal","HP-Pi-font","HP-Math8",
			"Adobe-Symbol-Encoding","HP-DeskTop","Ventura-Math","Microsoft-Publishing","Windows-31J",
			"GB2312","Big5","windows-1250","windows-1251","windows-1252","windows-1253","windows-1254",
			"windows-1255","windows-1256","windows-1257","windows-1258","TIS-620","HZ-GB-2312",
			"US-ASCII","X-JIS0208","ISO-8859-1","ISO-8859-2","ISO-8859-3","ISO-8859-4","ISO-8859-6",
			"ISO-8859-7","ISO-8859-8","ISO-8859-5","ISO-8859-9","EUC-JP","WINDOWS-31J");
	
	public static final List<String> BYTE_ORDER = Arrays.asList("big","little","middle");
	
	public static final List<String> LANGUAGE = Arrays.asList(
			"abk","ace","ach","ada","aar","afh","afr","afa","aka","akk","alb/sqi","alb","sqi","ale",
			"alg","amh","apa","ara","arc","arp","arn","arw","arm/hye","arm","hye","art","asm","ast",
			"ath","aus","ava","ave","awa","aym","aze","ban","bat","bal","bam","bai","bad","bnt","bas",
			"bak","baq/eus","baq","eus","btk","bej","bel","bem","ben","ber","bho","bih","bik","bin",
			"bis","bos","bra","bre","bug","bul","bua","bur/mya","bur","mya","cad","car","cat","cau",
			"ceb","cel","cai","chg","cmc","cha","che","chr","chy","chb","chi/zho","chi","zho","chn",
			"chp","cho","chu","chk","chv","cop","cor","cos","cre","crp","cpe","cpf","cpp","scr/hrv",
			"scr","hrv","cus","cze/ces","cze","ces","dak","dan","day","del","din","div","doi","dgr",
			"dra","dua","dut/nld","dut","nld","dum","dyu","dzo","efi","egy","eka","elx","eng","enm",
			"ang","epo","est","ewe","ewo","fan","fat","fao","fij","fin","fiu","fon","fre/fra","fre",
			"fra","frm","fro","fry","fur","ful","gaa","gla","glg","lug","gay","gba","gez","geo/kat",
			"geo","kat","ger/deu","ger","deu","gmh","goh","gem","gil","gon","gor","got","grb","grc",
			"gre/ell","gre","ell","grn","guj","gwi","hai","hau","haw","heb","her","hil","him","hin",
			"hmo","hit","hmn","hun","hup","iba","ice/isl","ice","isl","ido","ibo","ijo","ilo","inc",
			"ine","ind","ina","ile","iku","ipk","ira","gle","mga","sga","iro","ita","jpn","jav","jrb",
			"jpr","kab","kac","kal","kam","kan","kau","kaa","kar","kas","kaw","kaz","kha","khm","khi",
			"kho","kik","kmb","kin","kir","kom","kon","kok","kor","kos","kpe","kro","kua","kum","kur",
			"kru","kut","lad","lah","lam","lao","lat","lav","ltz","lez","lin","lit","nds","loz","lub",
			"lua","lui","smj","lun","luo","lus","mac/mkd","mac","mkd","mad","mag","mai","mak","mlg",
			"may/msa","may","msa","mal","map","mlt","mnc","mdr","man","mni","mno","glv","mao/mri","mao",
			"mri","mar","chm","mah","mwr","mas","myn","men","mic","min","mis","moh","mol","mkh","lol",
			"mon","mos","mul","mun","mus","nah","nau","nav","nde","nbl","ndo","nep","new","nia","nic",
			"ssa","niu","non","nai","sme","nor","nob","nno","nub","nym","nya","nyn","nyo","nzi","oji",
			"ori","orm","osa","oss","oto","pal","pau","pli","pam","pag","pan","pap","paa","per/fas","per",
			"fas","peo","phi","phn","pon","pol","por","pra","oci","pro","pus","que","roh","raj","rap",
			"rar","qaa-qtz","roa","rum/ron","rum","ron","rom","run","rus","sal","sam","smi","smo","sad",
			"sag","san","sat","srd","sas","sco","sel","sem","scc/srp","scc","srp","srr","shn","sna","sid",
			"sgn","bla","snd","sin","sit","sio","sms","den","sla","slo/slk","slo","slk","slv","sog","som",
			"son","snk","wen","nso","sot","sai","sma","smn","spa","suk","sux","sun","sus","swa","ssw",
			"swe","syr","tgl","tah","tai","tgk","tmh","tam","tat","tel","ter","tet","tha","tib/bod","tib",
			"bod","tig","tir","tem","tiv","tli","tpi","tkl","tog","ton","tsi","tso","tsn","tum","tup",
			"tur","ota","tuk","tut","tvl","tyv","twi","uga","uig","ukr","umb","und","urd","uzb","vai",
			"ven","vie","vol","vot","wak","wal","wln","war","was","wel/cym","wel","cym","wol","xho","sah",
			"yao","yap","yid","yor","ypk","znd","zap","zen","zha","zul","zun");
	
	public static final List<String> PAGE_ORDER = Arrays.asList("left-to-right","right-to-left");
	
	public static final List<String> REPRESENTATION_SEQUENCE = Arrays.asList("reading-order",
			"inverse-reading-order");
	
	public static final List<String> LINE_ORIENTATION = Arrays.asList("vertical","horizontal");
	
	public static final List<String> LINE_LAYOUT = Arrays.asList("right-to-left","left-to-right",
			"top-to-bottom","bottom-to-top");
	
	public static final List<String> CHARACTER_FLOW = Arrays.asList("right-to-left","left-to-right",
			"top-to-bottom","bottom-to-top","boustrophedon-start-on-right","boustrophedon start-on-left");

}
