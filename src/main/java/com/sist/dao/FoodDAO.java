package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	
	static
	{
		try
		{
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//기능 => ConnectionPool => Connection저장 : getConnection / disConnection
	
	
	/*
	 <select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
		SELECT no,name,poster,address,type
		FROM food
		WHERE
		<!-- 
			추가: prefix , suffix
			삭제: prefixOverrides, suffixOverrides
			
			<input type="checkbox" value="A" name="">양식
			
			WHERE (type LIKE '%양식%') OR (type LIKE '%일식%')
		 -->
		<trim prefix="(" suffix=")" prefixOverrides="OR|AND">  <!-- 맨 앞에있는 OR,AND는 지운다 -->
			<foreach collection="fdArr" item="fd">
				<trim prefix="OR"> <!-- type LIKE 앞에 OR가 붙인다 -->
					<choose>
						<when test="fd =='A'.toString()">
							type LIKE '%한식%'
						</when>
						<when test="fd =='B'.toString()">
							type LIKE '%양식%'
						</when>
						<when test="fd =='C'.toString()">
							type LIKE '%일식%'
						</when>
						<when test="fd =='D'.toString()">
							type LIKE '%중식%'
						</when>
						<when test="fd =='E'.toString()">
							type LIKE '%분식%'
						</when>
					</choose>
				</trim>
			</foreach>
		</trim>
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	
		VO / DAO (x)
		
		Model 
		 = List    vo
		 	|		|
		   []	   {}
	*/
	public static List<FoodVO> foodFindData(Map map)
	{
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData",map);
		session.close();
		return list;
	}
	/*
	<select id="foodFindCount" resultType="int" parameterType="hashmap">
		SELECT COUNT(*)
		FROM food
		WHERE
		<trim prefix="(" suffix=")" prefixOverrides="OR|AND">  <!-- 맨 앞에있는 OR,AND는 지운다 -->
			<foreach collection="fdArr" item="fd">
				<trim prefix="OR"> <!-- type LIKE 앞에 OR가 붙인다 -->
					<choose>
						<when test="fd =='A'.toString()">
							type LIKE '%한식%'
						</when>
						<when test="fd =='B'.toString()">
							type LIKE '%양식%'
						</when>
						<when test="fd =='C'.toString()">
							type LIKE '%일식%'
						</when>
						<when test="fd =='D'.toString()">
							type LIKE '%중식%'
						</when>
						<when test="fd =='E'.toString()">
							type LIKE '%분식%'
						</when>
					</choose>
				</trim>
			</foreach>
		</trim>
	</select>
	 */
	public static int foodFindCount(Map map)
	{
		SqlSession session = ssf.openSession();
		int count = session.selectOne("foodFindCount",map);
		session.close();
		return count;
	}
}
