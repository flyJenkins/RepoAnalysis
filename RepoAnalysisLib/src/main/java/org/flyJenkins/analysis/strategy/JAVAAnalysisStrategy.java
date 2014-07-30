package org.flyJenkins.analysis.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ProjectDto;
import org.flyJenkins.analysis.model.SearchCodeDto;
import org.flyJenkins.analysis.model.SearchItemDto;
import org.flyJenkins.analysis.service.GitHubApiManager;


public class JAVAAnalysisStrategy implements LanguageAnalysisManager {
	
	private GitHubApiManager gitHubApiManager;
	
	public void setGitHubApiManager(GitHubApiManager gitHubApiManager) {
		this.gitHubApiManager = gitHubApiManager;
	}

	@Override
	public void getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd, ProjectDto projectDto) {
		gitHubRepoCmd.setQuery("pom");
		gitHubRepoCmd.setLanguage("xml");

		// Project 메이븐인지 체크
		SearchCodeDto searchCodeDto = gitHubApiManager.getSearchFileCode(gitHubRepoCmd);		
		if (searchCodeDto.getTotal_count() > 0) {
			projectDto.setAnalysisChance("Y");
			projectDto.setBuildType("maven");
				
			// POM 설정 정보 담기
			List<Map<String, String>> confList = new ArrayList<Map<String, String>>();
			Iterator<SearchItemDto> itSearchItem = searchCodeDto.getItems().iterator();
			while(itSearchItem.hasNext()) {
				SearchItemDto searchItemDto = itSearchItem.next();
				Map<String, String> confMap = new HashMap<String, String>();
				confMap.put("name", searchItemDto.getName());
				confMap.put("path", searchItemDto.getPath());
				confList.add(confMap);
			}
			projectDto.setConfList(confList);

			// Project Spring 체크
			gitHubRepoCmd.setQuery("application");
			gitHubRepoCmd.setLanguage("xml");

			searchCodeDto = gitHubApiManager.getSearchFileCode(gitHubRepoCmd);
			if (searchCodeDto.getTotal_count() > 0) {
				projectDto.setProjectType("spring");
			}
		}
	}
}
