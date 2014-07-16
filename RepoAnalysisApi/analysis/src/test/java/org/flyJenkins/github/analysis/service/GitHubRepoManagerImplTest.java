package org.flyJenkins.github.analysis.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.flyJenkins.github.analysis.model.CommitDto;
import org.flyJenkins.github.analysis.model.GitHubRepoCmd;
import org.flyJenkins.github.analysis.model.ReposDto;
import org.flyJenkins.github.analysis.model.SearchCodeDto;
import org.flyJenkins.github.analysis.service.GitHubApiManager;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:META-INF/spring/applicationContext*"})
public class GitHubRepoManagerImplTest {
	
	@Autowired
	private GitHubApiManager gitHubRepoManager;
	
	/**
	 * 프로젝트 정보 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	@Ignore
	@Test
	public void getProjectInfoTest() {
		GitHubRepoCmd gitHubRepoCmd= new GitHubRepoCmd();
		gitHubRepoCmd.setOwner("realwater");
		gitHubRepoCmd.setRepo("GitHubApi");
		
		ReposDto reposDto = gitHubRepoManager.getProjectInfo(gitHubRepoCmd);
		Assert.assertNotNull(reposDto);
	}
		
	/**
	 * 프로젝트 내 코드 검색
	 * @param gitHubRepoCmd
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@Ignore
	@Test
	public void getSearchFileCodeTest() {
		GitHubRepoCmd gitHubRepoCmd= new GitHubRepoCmd();
		gitHubRepoCmd.setQuery("pom");
		gitHubRepoCmd.setLanguage("xml");
		gitHubRepoCmd.setOwner("realwater");
		gitHubRepoCmd.setRepo("GitHubApi");
		
		SearchCodeDto searchCodeDto = gitHubRepoManager.getSearchFileCode(gitHubRepoCmd);
		Assert.assertNotNull(searchCodeDto);
	}
		
	/**
	 * 프로젝트 커밋 정보 조회
	 * @param gitHubRepoCmd
	 * @return
	 */
	@Ignore
	@Test
	public void getProjectCommitInfoTest() {
		GitHubRepoCmd gitHubRepoCmd= new GitHubRepoCmd();
		gitHubRepoCmd.setOwner("realwater");
		gitHubRepoCmd.setRepo("GitHubApi");
		
		List<CommitDto> commitDtoList = gitHubRepoManager.getProjectCommitInfo(gitHubRepoCmd);
		Assert.assertNotNull(commitDtoList);
	}

}
