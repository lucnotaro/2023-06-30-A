package it.polito.tdp.exam.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;

public class Model {
	
	private BaseballDAO dao;
	private Graph<Integer,DefaultWeightedEdge> graph;
	private List<Integer> allNodes;
	
	public Model() {
		this.dao=new BaseballDAO();
		this.allNodes=new ArrayList<>();
	}
	
	public void buildGraph(String team) {
		this.loadNodes(team);
		this.graph=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.graph,this.allNodes);
		for(int y1:this.graph.vertexSet()) {
			for(int y2:this.graph.vertexSet()) {
				if(y1!=y2 && this.graph.getEdge(y1,y2)==null) {
					Graphs.addEdge(this.graph,y1,y2,this.getWeight(team,y1,y2));
				}
			}
		}
		System.out.println("v: "+this.graph.vertexSet().size());
		System.out.println("e: "+this.graph.edgeSet().size());
	}
	
	private void loadNodes(String t) {
		 if(this.allNodes.isEmpty()) {
			 this.allNodes.addAll(this.dao.getYearsOf(t));
		 }
	}
	
	public List<String> getTeams(){
		return this.dao.getTeams();
	}
	
	public List<Integer> getNodes(){
		return this.allNodes;
	}
	
	public List<Successore> getSuccessorEdges(Integer year){
		List<Integer> successori=Graphs.successorListOf(this.graph,year);
		List<Successore> successorEdges=new ArrayList<>();
//		Map<Integer,Successore> successorEdgesMap=new HashMap<>();
		for(int y:successori) {
			successorEdges.add(new Successore(y,(int) this.graph.getEdgeWeight(this.graph.getEdge(year,y))));
		}
		Collections.sort(successorEdges);
/*		for(Successore s:successorEdges) {
			successorEdgesMap.put(s.getE(),s);
		}*/
		return successorEdges;
	}
	
	public Integer getWeight(String t,Integer y1,Integer y2) {
		List<String> p1=new ArrayList<>(this.dao.getPlayersOfIn(t,y1));
		List<String> p2=new ArrayList<>(this.dao.getPlayersOfIn(t,y2));
		Set<String> peso=new HashSet<>();
		for(String i:p1) {
			for(String j:p2) {
				if(i.equals(j))
					peso.add(i);
			}
		}
		return peso.size();
	}
	
	
	public Integer getVsize() {
		return this.graph.vertexSet().size();
	}
	public Integer getEsize() {
		return this.graph.edgeSet().size();
	}
}
