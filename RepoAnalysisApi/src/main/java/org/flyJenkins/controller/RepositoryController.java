package org.flyJenkins.controller;

import javax.servlet.http.HttpServletRequest;

import org.flyJenkins.cache.redis.model.RedisCacheDto;
import org.flyJenkins.cache.redis.service.RedisCacheManager;
import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ProjectDto;
import org.flyJenkins.analysis.service.GitHubApiManager;
import org.flyJenkins.analysis.service.GitRepoAnalysisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/repos")
public class RepositoryController {

	@Autowired
	private GitRepoAnalysisManager gitRepoAnalysisManagerImpl;

	@Autowired
	private GitHubApiManager gitHubRepoManagerImpl;

	@Autowired
	private RedisCacheManager redisCacheManagerImpl;
	
	/**
	 * GIT 저장소 분석
	 * 2014.05.11
	 * @author realwater
	 */
	@RequestMapping(value="/{owner}/{repo}/git/analysis", method=RequestMethod.GET)
	public void gitAnalysisInfo(
			@PathVariable("owner") String owner,
			@PathVariable("repo") String repo,
			HttpServletRequest request,
			ModelMap mode) {

		String cacheKey = owner+"_"+repo+"_analysis";
		
		// Cache Data 조회
		ProjectDto projectDto = (ProjectDto) redisCacheManagerImpl.getCacheValue(cacheKey);

		// Data Analysis 및 Cache 동기화
		if (projectDto == null) {
			GitHubRepoCmd gitHubRepoCmd = new GitHubRepoCmd();
			gitHubRepoCmd.setOwner(owner);
			gitHubRepoCmd.setRepo(repo);
			
			// 프로젝트 분석
			projectDto = gitRepoAnalysisManagerImpl.getGitAnalysisInfo(gitHubRepoCmd);
				
			/**
			 * 캐쉬 데이터 저장
			 */
			RedisCacheDto redisCacheDto = new RedisCacheDto(); 
			redisCacheDto.setChannelKey(cacheKey);
			redisCacheDto.setValue(projectDto);
			redisCacheDto.setExpireTime(1800);
			redisCacheManagerImpl.saveCacheValue(redisCacheDto);
		}
		
		mode.clear();
		mode.addAttribute("projectDto", projectDto);
	}

	/**
	 * 저장소 디렉토리 정보
	 * 2014.04.16
	 * @author realwater
	 */
	@RequestMapping(value="/{owner}/{repo}/contents/**", method=RequestMethod.GET)
	@ResponseBody
	public String repositoryInfo(
			@PathVariable("owner") String owner,
			@PathVariable("repo") String repo,
			HttpServletRequest request,
			ModelMap model) {

		String requestPath = request.getRequestURI();
		String cacheKey = owner+"/"+repo+requestPath;
		
		// Cache Data 조회
		String resultJson = (String) redisCacheManagerImpl.getCacheValue(cacheKey);
		
		if (resultJson == null) {
			GitHubRepoCmd gitHubRepoCmd = new GitHubRepoCmd();
			gitHubRepoCmd.setRequestPath(requestPath);
			
			resultJson = gitHubRepoManagerImpl.getRepositoryInfo(gitHubRepoCmd);
			
			/**
			 * 캐쉬 데이터 저장
			 */
			RedisCacheDto redisCacheDto = new RedisCacheDto(); 
			redisCacheDto.setChannelKey(cacheKey);
			redisCacheDto.setValue(resultJson);
			redisCacheDto.setExpireTime(1800);
			redisCacheManagerImpl.saveCacheValue(redisCacheDto);
		}

		return resultJson;
	}

}
