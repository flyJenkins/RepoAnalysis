package org.flyJenkins.analysis.service;

import java.util.List;

import org.flyJenkins.analysis.model.CommitDto;
import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ReposDto;
import org.flyJenkins.analysis.model.SearchCodeDto;


public interface GitHubApiManager {
	
	/**
	 * �꾨줈�앺듃 �뺣낫 議고쉶
	 * @param gitHubRepoCmd
	 * @return
	 */
	public ReposDto getProjectInfo(GitHubRepoCmd gitHubRepoCmd);
		
	/**
	 * �꾨줈�앺듃 ��肄붾뱶 寃�깋
	 * @param gitHubRepoCmd
	 * @return
	 */
	public SearchCodeDto getSearchFileCode(GitHubRepoCmd gitHubRepoCmd);
		
	/**
	 * �꾨줈�앺듃 而ㅻ컠 �뺣낫 議고쉶
	 * @param gitHubRepoCmd
	 * @return
	 */
	public List<CommitDto> getProjectCommitInfo(GitHubRepoCmd gitHubRepoCmd);
	
	/**
	 * �꾨줈�앺듃 ��옣��寃쎈줈 議고쉶
	 * @param gitHubRepoCmd
	 * @return
	 */
	public String getRepositoryInfo(GitHubRepoCmd gitHubRepoCmd);
}
