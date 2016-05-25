package fin.info.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fin.info.server.annotation.Reporter;
import fin.info.server.model.AdvanceQuery;
import fin.info.server.model.SuggestResult;
import fin.info.server.service.SearchService;
import fin.info.server.service.SuggestService;


import java.util.Map;

@Controller
@RequestMapping(value = "/search")
@SessionAttributes("frs") 
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private SuggestService suggestService;

	@RequestMapping(method = RequestMethod.GET)
	public String tosearch() {
		return "search";
	}

	@ResponseBody
	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	public SuggestResult suggest(@RequestParam String query) {
		return suggestService.suggest(query);
	}
	
	@Reporter
	@RequestMapping(value = "/s", method = RequestMethod.GET)
	public String defaultSearch(@RequestParam(required=false,defaultValue="") String q, @RequestParam(required=false,defaultValue="1") int start, @RequestParam(required=false,defaultValue="0") int needfacet, ModelMap map) {
		Map<String, Object> result = searchService.defaultSearch(q, start, needfacet==0 ? false : true);
		map.addAttribute("paramq", q);
		map.addAttribute("q", q);
		map.addAttribute("path", "search/s");
		map.addAttribute("qtime", result.get("qtime"));
		map.addAttribute("pb", result.get("pb"));
		if(needfacet==1){
			map.addAttribute("frs",result.get("frs"));
		}
		return "search";
	}
	
	@Reporter
	@RequestMapping(value = "/hs", method = RequestMethod.GET)
	public String advanceSearch(@ModelAttribute AdvanceQuery advanceQuery, @RequestParam(required=false,defaultValue="0") int needfacet, ModelMap map) {
		Map<String, Object> result = searchService.advanceSearch(advanceQuery, needfacet==0 ? false : true);
		map.addAttribute("paramq", advanceQuery.getGetParams());
		map.addAttribute("q", advanceQuery.getQ());
		map.addAttribute("path", "search/hs");
		map.addAttribute("qtime", result.get("qtime"));
		map.addAttribute("pb", result.get("pb"));
		if(needfacet==1){
			map.addAttribute("frs",result.get("frs"));
		}
		return "search";
	}


}