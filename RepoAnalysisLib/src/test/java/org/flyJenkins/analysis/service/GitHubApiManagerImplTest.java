package org.flyJenkins.analysis.service;

import org.flyJenkins.analysis.model.GitHubRepoCmd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/applicationContext*"})
public class GitHubApiManagerImplTest {

	@Autowired
	private GitHubApiManager GitHubApiManagerImpl;
	
	@Test
	public void getSearchFileCodeTest() {
		GitHubRepoCmd gitHubRepoCmd = new GitHubRepoCmd();
		gitHubRepoCmd.setOwner("gitHubRepoCmd");
		gitHubRepoCmd.setRepo("RepoAnalysis");
		gitHubRepoCmd.setQuery("pom");
		gitHubRepoCmd.setLanguage("xml");
		
		GitHubApiManagerImpl.getSearchFileCode(gitHubRepoCmd);
	}
}
