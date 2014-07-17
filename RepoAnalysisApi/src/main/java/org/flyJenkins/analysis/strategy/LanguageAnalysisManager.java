package org.flyJenkins.analysis.strategy;

import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ProjectDto;
import org.flyJenkins.analysis.service.GitHubApiManager;


public interface LanguageAnalysisManager {
	
	public void setGitHubApiManager(GitHubApiManager gitHubApiManager);

	public void getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd, ProjectDto projectDto);
}