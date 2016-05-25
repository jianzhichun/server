package fin.info.server.jstl.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class PageTag extends TagSupport {

	private PageBean<?> pageBean;

	private String path;

	private String q;

	public void setPath(String path) {
		this.path = path;
	}


	public void setQ(String q) {
		this.q = q;
	}

	public void setPageBean(PageBean<?> pageBean) {
		this.pageBean = pageBean;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String outStr = makeString();
		try {
			out.write(outStr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	private String makeString() {

		StringBuffer sb = new StringBuffer();

		String baseUri = path + "?q=" + q + "&start=";

		if (pageBean != null && pageBean.getItems() != null && pageBean.getItems().size() > 0) {

			sb.append("<ul class='pagination'><li><a href='" + baseUri
					+ "1'>first </a></li>");
			if (pageBean.getCurPage() > 1) {
				sb.append("<li><a href='" + baseUri + (pageBean.getCurPage() - 1)
						+ "'>pre</a></li>");
			}
			int offset = pageBean.getCurPage() - 4;
			offset = offset < 1 ? 1 : offset;
			int last = offset + 7;
			last = last > pageBean.getPageNum() + 1 ? pageBean.getPageNum() + 1 : last;
			for (int i = offset; i < last; i++) {
				sb.append("<li><a href='" + baseUri + i + "'> " + i + " </a></li>");
			}

			if (pageBean.getCurPage() != pageBean.getPageNum()) {
				sb.append("<li class='next'><a href='" + baseUri + (pageBean.getCurPage() + 1)
						+ "'>next </a></li>");
				sb.append("<li class='next'><a href='" + baseUri + pageBean.getPageNum()
						+ "'>last </a></li>");
			}

		}

		return sb.toString();
	}

}
