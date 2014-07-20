package org.flyJenkins.analysis.service;

import java.util.List;

import org.flyJenkins.analysis.model.CommitDto;
import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ReposDto;
import org.flyJenkins.analysis.model.SearchCodeDto;


public interface GitHubApiManager {
	
	/**
	 * 프로젝트 정보 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	public ReposDto getProjectInfo(GitHubRepoCmd gitHubRepoCmd);
		
	/**
	 * 프로젝트 내 코드 검색
	 * @param gitHubRepoCmd
	 * @return
	 */
	public SearchCodeDto getSearchFileCode(GitHubRepoCmd gitHubRepoCmd);
		
	/**
	 * 프로젝트 커밋 정보 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	public List<CommitDto> getProjectCommitInfo(GitHubRepoCmd gitHubRepoCmd);
	
	/**
	 * 프로젝트 저장소 경로 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	public String getRepositoryInfo(GitHubRepoCmd gitHubRepoCmd);
}
