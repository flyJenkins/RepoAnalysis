package org.flyJenkins.analysis.service;

import java.util.List;

import org.flyJenkins.analysis.define.GitApiDefineEnum;
import org.flyJenkins.analysis.model.CommitDto;
import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ProjectDto;
import org.flyJenkins.analysis.model.ReposDto;
import org.flyJenkins.analysis.strategy.LanguageAnalysisManager;
import org.springframework.beans.factory.annotation.Autowired;

public class GitRepoAnalysisManagerImpl implements GitRepoAnalysisManager {
	
	@Autowired
	private GitHubApiManager gitHubApiManagerImpl;

	@Override
	public ProjectDto getGitAnalysisInfo(GitHubRepoCmd gitHubRepoCmd) {
		
		// 1. 프로젝트 정보 조회
		ReposDto reposDto = gitHubApiManagerImpl.getProjectInfo(gitHubRepoCmd);
		ProjectDto projectDto = new ProjectDto();
		
		// 2. 프로젝트 내 타입 조회
		if (reposDto != null) {
			projectDto.setProjectName(gitHubRepoCmd.getRepo());
			
			String projectLanguage = reposDto.getLanguage().toUpperCase();
			projectDto.setLanguage(projectLanguage);
			
			StringBuffer sbFileClassName = new StringBuffer();
			sbFileClassName.append(GitApiDefineEnum.GIT_STRATEGY_PACKAGE);
			sbFileClassName.append(projectLanguage);
			sbFileClassName.append(GitApiDefineEnum.GIT_STRATEGY_NAME);
			
			String strategyClassName = sbFileClassName.toString();
			
			// 맞는 분석 클래스가 있을 경우 분석 처리
			if (!strategyClassName.isEmpty()) {
				Class<?> strategyClass;
				try {
					strategyClass = Class.forName(strategyClassName);
					LanguageAnalysisManager gitHubAnalysisStrategy = (LanguageAnalysisManager) strategyClass.newInstance();
					// call by reference로 projectDto 정보 생성
					gitHubAnalysisStrategy.setGitHubApiManager(gitHubApiManagerImpl);
					gitHubAnalysisStrategy.getGitAnalysisInfo(gitHubRepoCmd, projectDto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
			// 최신 리비전 sha 정보가 있으면 저장한다.
			List<CommitDto> commitDtoList = gitHubApiManagerImpl.getProjectCommitInfo(gitHubRepoCmd);
			if (!commitDtoList.isEmpty()) {
				projectDto.setCommitSha(commitDtoList.get(0).getSha());
			}		
		}
		
		return projectDto;
	}

}
