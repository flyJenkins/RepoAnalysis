package org.flyJenkins.analysis.service;

import java.util.ArrayList;
import java.util.List;

import org.flyJenkins.analysis.define.GitApiDefine;
import org.flyJenkins.analysis.model.CommitDto;
import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ReposDto;
import org.flyJenkins.analysis.model.SearchCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.thoughtworks.xstream.XStream;

import scala.actors.threadpool.Arrays;

public class GitHubApiManagerImpl implements GitHubApiManager {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private XStream xstreamManager;
	
	/**
	 * 프로젝트 정보 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	@Override
	public ReposDto getProjectInfo(GitHubRepoCmd gitHubRepoCmd) {
		StringBuffer sbApiUrl = new StringBuffer();
		sbApiUrl.append(GitApiDefine.GIT_API_REPOS_URL);
		sbApiUrl.append("/"+gitHubRepoCmd.getOwner());
		sbApiUrl.append("/"+gitHubRepoCmd.getRepo());
		
		ReposDto repos = new ReposDto();
		try {
			// 확장자가 있으면 Object로 호출
			repos = restTemplate.getForObject(sbApiUrl.toString(), ReposDto.class);
		} catch (final HttpClientErrorException e) {
		    e.getResponseBodyAsString();
		}
		
		return repos;
	}

	/**
	 * 프로젝트 내 코드 검색
	 * @param gitHubRepoCmd
	 * @return
	 */
	@Override
	public SearchCodeDto getSearchFileCode(GitHubRepoCmd gitHubRepoCmd) {
		StringBuffer sbApiUrl = new StringBuffer();
		sbApiUrl.append(GitApiDefine.GIT_API_SEARCH_URL);
		sbApiUrl.append("/code");
		sbApiUrl.append("?q="+gitHubRepoCmd.getQuery());
		sbApiUrl.append(" in:file");
		sbApiUrl.append(" language:"+gitHubRepoCmd.getLanguage());
		sbApiUrl.append(" repo:"+gitHubRepoCmd.getOwner()+"/"+gitHubRepoCmd.getRepo());
		
		SearchCodeDto searchCodeDto = new SearchCodeDto();
		try {
			// 확장자가 있으면 Object로 호출
			searchCodeDto = restTemplate.getForObject(sbApiUrl.toString(), SearchCodeDto.class);
		} catch (final HttpClientErrorException e) {
		    e.getResponseBodyAsString();
		}
		
		return searchCodeDto;
	}
	
	/**
	 * 프로젝트 커밋 정보 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommitDto> getProjectCommitInfo(GitHubRepoCmd gitHubRepoCmd) {
		StringBuffer sbApiUrl = new StringBuffer();
		sbApiUrl.append(GitApiDefine.GIT_API_REPOS_URL);
		sbApiUrl.append("/"+gitHubRepoCmd.getOwner());
		sbApiUrl.append("/"+gitHubRepoCmd.getRepo());
		sbApiUrl.append("/commits");
		
		CommitDto[] commitDtos = null;
		List<CommitDto> commitDtoList = new ArrayList<CommitDto>();
		try {
			// 확장자가 있으면 Object로 호출
			commitDtos = restTemplate.getForObject(sbApiUrl.toString(), CommitDto[].class);
		    commitDtoList = Arrays.asList(commitDtos);
		} catch (final HttpClientErrorException e) {
		    e.getResponseBodyAsString();
		}
		
		return commitDtoList;
	}
	
	/**
	 * 프로젝트 저장소 경로 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	@Override
	public String getRepositoryInfo(GitHubRepoCmd gitHubRepoCmd) {
		String url = GitApiDefine.GIT_API_URL+gitHubRepoCmd.getRequestPath();
		
		ReposDto repos = new ReposDto();
		ReposDto[] reposList = {};
		String resultJson = "";
		
		try {
			// 확장자가 있으면 Object로 호출
			if (gitHubRepoCmd.getRequestPath().indexOf('.') > 0) {
				xstreamManager.alias("repos", ReposDto.class);
				repos = restTemplate.getForObject(url, ReposDto.class);
				resultJson = xstreamManager.toXML(repos);
			} else { // 확장자가 있으면 Object로 호출
				xstreamManager.alias("repos", ReposDto[].class);
				reposList = restTemplate.getForObject(url, ReposDto[].class);
				resultJson = xstreamManager.toXML(reposList);
			}
		} catch (final HttpClientErrorException e) {
		    resultJson = e.getResponseBodyAsString();
		}
		
		return resultJson;
	}
}
