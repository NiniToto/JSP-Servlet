package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.filter.wrapper.PartWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"att_no", "bo_no"})
public class AttatchVO implements Serializable {

	public AttatchVO(PartWrapper att_file) {
		super();
		this.att_file = att_file;
		this.att_filename = att_file.getOriginalFilename();
		this.att_savename = att_file.getSaveName();
		this.att_filesize = att_file.getSize();
		this.att_mime = att_file.getContentType();
		this.att_fancysize = FileUtils.byteCountToDisplaySize(att_filesize);
	}

	private Integer att_no;
	private Integer bo_no;
	@NotBlank
	@Size(max = 50)
	private String att_filename;
	@NotBlank
	@Size(max = 100)
	private String att_savename;
	@Size(max = 40)
	private String att_mime;
	@NotBlank
	@Size(max = 12)
	private Long att_filesize;
	@NotBlank
	@Size(max = 10)
	private String att_fancysize; //ex)1GB
	@Size(max = 6)
	private Integer att_downcount;
	
	private PartWrapper att_file;
	
	public void saveFile(File folder) throws IOException {
		if(att_file != null && att_file.getSize() >0) {
			att_file.saveToRealPath(folder);
		}
	}
}
