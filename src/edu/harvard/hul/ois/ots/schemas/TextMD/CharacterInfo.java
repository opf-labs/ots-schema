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

public class CharacterInfo {
	
	private String charset;
	private String byteOrder;
	private Integer byteSize;
	private String linebreak;
	private String characterSizeEncoding;
	private String characterSizeValue;
	
	public CharacterInfo() { }

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) throws InvalidTextMDValue {
		if(EnumeratedValues.CHARSET.contains(charset)) {
			this.charset = charset;
		}
		else {
			throw new InvalidTextMDValue(charset);
		}
	}

	public String getByteOrder() {
		return byteOrder;
	}

	public void setByteOrder(String byteOrder) throws InvalidTextMDValue {
		if(EnumeratedValues.BYTE_ORDER.contains(byteOrder)) {
			this.byteOrder = byteOrder;
		}
		else {
			throw new InvalidTextMDValue(byteOrder);
		}
	}

	public Integer getByteSize() {
		return byteSize;
	}

	public void setByteSize(Integer byteSize) {
		this.byteSize = byteSize;
	}

	public String getLinebreak() {
		return linebreak;
	}

	public void setLinebreak(String linebreak) throws InvalidTextMDValue {
		if(EnumeratedValues.LINEBREAK.contains(linebreak)) {
			this.linebreak = linebreak;
		}
		else {
			throw new InvalidTextMDValue(linebreak);
		}
	}

	public String getCharacterSizeEncoding() {
		return characterSizeEncoding;
	}

	public void setCharacterSizeEncoding(String characterSizeEncoding) {
		this.characterSizeEncoding = characterSizeEncoding;
	}

	public String getCharacterSizeValue() {
		return characterSizeValue;
	}

	public void setCharacterSizeValue(String characterSizeValue) {
		this.characterSizeValue = characterSizeValue;
	}

}
