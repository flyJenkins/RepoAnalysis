package org.flyJenkins.analysis.strategy;

import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.flyJenkins.analysis.model.ProjectDto;
import org.flyJenkins.analysis.service.GitHubApiManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/application*"})
public class AnalysisStrategyTest {
	
	@Autowired
	private GitHubApiManager gitHubApiManager;
	
	private JAVAAnalysisStrategy javaAnalysisStrategy;
	
	@Before
	public void setUp() {
		javaAnalysisStrategy = new JAVAAnalysisStrategy();
	}

	//@Ignore
	@Test
	public void getJavaGitAnalysisInfoTest() {
		GitHubRepoCmd gitHubRepoCmd = new GitHubRepoCmd();
		gitHubRepoCmd.setOwner("gitHubRepoCmd");
		gitHubRepoCmd.setRepo("RepoAnalysis");
		
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectName("RepoAnalysis");
		projectDto.setLanguage("JAVA");
		
		javaAnalysisStrategy.setGitHubApiManager(gitHubApiManager);
		javaAnalysisStrategy.getGitAnalysisInfo(gitHubRepoCmd, projectDto);
	}
}
