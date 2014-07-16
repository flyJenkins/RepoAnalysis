package org.flyJenkins.github.analysis.service;

import org.flyJenkins.github.analysis.model.GitHubRepoCmd;
import org.flyJenkins.github.analysis.model.ProjectDto;


public interface GitRepoAnalysisManager {
	
	public ProjectDto getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd);
}
