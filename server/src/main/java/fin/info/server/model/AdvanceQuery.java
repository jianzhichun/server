package fin.info.server.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
public class AdvanceQuery implements Serializable {

	private String q;

	private String starttime;

	private String endtime;

	private int titleWeight;

	private int titlepyWeigth;

	private int authorWeight;

	private int authorpyWeight;

	private int descriptionWeight;

	private int contentWeight;

	private String sortField;

	private String order;

	private int start;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getTitleWeight() {
		return titleWeight;
	}

	public void setTitleWeight(int titleWeight) {
		this.titleWeight = titleWeight;
	}

	public int getTitlepyWeigth() {
		return titlepyWeigth;
	}

	public void setTitlepyWeigth(int titlepyWeigth) {
		this.titlepyWeigth = titlepyWeigth;
	}

	public int getAuthorWeight() {
		return authorWeight;
	}

	public void setAuthorWeight(int authorWeight) {
		this.authorWeight = authorWeight;
	}

	public int getAuthorpyWeight() {
		return authorpyWeight;
	}

	public void setAuthorpyWeight(int authorpyWeight) {
		this.authorpyWeight = authorpyWeight;
	}

	public int getDescriptionWeight() {
		return descriptionWeight;
	}

	public void setDescriptionWeight(int descriptionWeight) {
		this.descriptionWeight = descriptionWeight;
	}

	public int getContentWeight() {
		return contentWeight;
	}

	public void setContentWeight(int contentWeight) {
		this.contentWeight = contentWeight;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getQf() {
		return "title^" + titleWeight + " author^" + authorWeight + " description^" + descriptionWeight + " content^"
				+ contentWeight + (0 == titlepyWeigth ? "" : " title_py^" + titlepyWeigth)
				+ (0 == authorpyWeight ? "" : " author_py^" + authorpyWeight);
	}

	// [2014-04-11T00:00:00Z TO 2014-04-13T00:00:00Z]
	public String getTimeRange() {
		if (StringUtils.isBlank(starttime) || StringUtils.isBlank(endtime))
			return null;
		return "[" + starttime + "T00:00:00Z TO " + endtime + "T00:00:00Z]";
	}

	// 金融++&starttime=2016-04-20&endtime=2016-04-26&titleWeight=100&titlepyWeight=100&authorWeight=50&authorpyWeight=50&descriptionWeight=50&contentWeight=40&sortField=time&order=desc
	public String getGetParams() {
		return q + "&starttime=" + starttime + "&endtime=" + endtime + "&titleWeight=" + titleWeight + "&titlepyWeight="
				+ titlepyWeigth + "&authorWeight=" + authorWeight + "&authorpyWeight=" + authorpyWeight
				+ "&descriptionWeight=" + descriptionWeight + "&contentWeight=" + contentWeight + "&sortField="
				+ sortField + "&order=" + order;
	}

}
