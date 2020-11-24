package com.huxley.plagiarism.integrator.database;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HuxleyRepository {
	
	@Autowired	
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> listWaiting(Integer maxResults) {
		String query = 
				"select " +
				"	i.id as institution_id, " + 
				"	upper(i.name) as institution_name, " + 
				"	g.id as class_id, " +
				"	upper(g.name) as class_name, " +
				"	q.id as survey_id, " +
				"	upper(q.title) as survey_name, " +
				"	p.id as problem_id, " +
				"	upper(coalesce(ppt.name, pen.name)) as problem_name, " +
				"	l.id as language_id, " +
				"	upper(l.name) as language_name, " +
				"	s.id as submission_id, " +
				"	s.filename as filename " + 
				"from public.user u " + 
				"inner join public.user_group uc on (uc.user_id = u.id) " + 
				"inner join public.group g on (g.id = uc.group_id) " + 
				"inner join public.institution i on (i.id = g.institution_id) " + 
				"inner join public.questionnaire q on (q.group_id = g.id) " + 
				"inner join public.questionnaire_problem qp on (qp.questionnaire_id = q.id) " + 
				"inner join public.problem p on (p.id = qp.problem_id) " +
				"left outer join public.problem_i18n ppt on (ppt.problem_id = p.id and ppt.locale = 'pt_BR') " + 
				"left outer join public.problem_i18n pen on (pen.problem_id = p.id and ppt.locale = 'en_US') " + 
				"inner join public.submission s on (s.problem_id = p.id and s.user_id = u.id) " + 
				"inner join public.language l on (l.id = s.language_id) " + 
				"where 1 = 1  " + 
				"and s.submission_date <= q.end_date " + 
				"and s.evaluation = 0  " + 
				"and not exists (select 1 from public.questionnaire_submission where questionnaire_id = q.id and submission_id = s.id) " +
				"limit ?";

		return this.jdbcTemplate.queryForList(query, maxResults);
	}
	
	public List<Map<String, Object>> listSurveys() {
		String query =
				"select " + 
				"	distinct " +
				"	i.id as institution_id, " + 
				"	upper(i.name) as institution_name, " + 
				"	g.id as class_id, " +
				"	upper(g.name) as class_name, " +
				"	q.id as survey_id, " +
				"	upper(q.title) as survey_name, " +
				"	p.id as problem_id, " +
				"	upper(p.name) as problem_name, " +
				"	l.id as language_id, " +
				"	upper(l.name) as language_name " +
				"from public.institution i " +
				"inner join public.group g on (g.institution_id = i.id)  " +
				"inner join public.questionnaire q on (q.group_id = g.id)  " +
				"inner join public.questionnaire_problem qp on (qp.questionnaire_id = q.id)  " +
				"inner join public.problem p on (p.id = qp.problem_id)  " +
				"inner join public.questionnaire_user qu on (qu.questionnaire_id = q.id)  " +
				"inner join public.user u on (u.id = qu.user_id)  " +
				"inner join public.submission s on (s.problem_id = p.id and s.user_id = u.id)  " +
				"inner join public.language l on (l.id = s.language_id)  ";
		
		return this.jdbcTemplate.queryForList(query);
	}
	
	public void setSentToPlagiarismDetection(Long questionnaireId, Long submissionId, Boolean status) {
		String query = "insert public.questionnaire_submission values(?, ?, ?)";
		this.jdbcTemplate.update(query, questionnaireId, submissionId, status);
	}
	
	public void saveOrUpdateSuspectedPlagiarism(Long sourceSubmissionId, Long targetSubmissionId, BigDecimal similarity) {
		String querySelect = "select count(*) from public.submission s1, public.submission s2 where s1.id = ? and s2.id = ? and s1.user_id = s2.user_id";
		
		Long mesmoUsuario = this.jdbcTemplate.queryForObject(querySelect, Long.class, sourceSubmissionId, targetSubmissionId);
		
		if (Long.valueOf(1).equals(mesmoUsuario)) {
			return;
		}
		
		querySelect = "select id from public.plagiarism where submission1_id = ? and submission2_id = ?";

		Map<String, Object> queryResult = this.jdbcTemplate.queryForMap(querySelect, sourceSubmissionId, targetSubmissionId);

		
		if (queryResult.containsKey("id")) {
			Long id = (Long) queryResult.get("id");
			
			String queryUpdate = "update public.plagiarism set percentage = ? where id = ?";
			
			this.jdbcTemplate.update(queryUpdate, similarity, id);
		} else  {
			String queryInsert = "insert into public.plagiarism values (nextval('plagiarism_id_seq'), 1, ?, ?, ?, 0)";	
			
			this.jdbcTemplate.update(queryInsert, similarity, sourceSubmissionId, targetSubmissionId);
		}
	}

}
