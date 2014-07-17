package org.flyJenkins.analysis.service;

import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ProjectDto;


public interface GitRepoAnalysisManager {
	
	public ProjectDto getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd);
}
