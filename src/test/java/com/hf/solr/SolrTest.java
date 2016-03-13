package com.hf.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;


public class SolrTest {
	
	public static final String SOLR_URL = "http://127.0.0.1:8983/solr/collection1";
	@Test
	public void testQuery() throws Exception, IOException{
		HttpSolrClient httpSolrClient=new HttpSolrClient(SOLR_URL);
		SolrQuery query = new SolrQuery();
		query.set("q","*:*");//查询所有数据
		QueryResponse rsp =httpSolrClient.query(query);
		SolrDocumentList list = rsp.getResults();
		
		for (int i = 0; i < list.size(); i++) {
		   SolrDocument sd = list.get(i);
		   String id = (String) sd.getFieldValue("id");
		   String name = (String) sd.getFieldValue("name");
		   System.out.println(id+"---"+name);
		}
		httpSolrClient.close();
	}
	
}
