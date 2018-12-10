package kr.co.jboard1.dao;

public class SQL {
	public static final String BOARD_COUNT = "select count(*) from JB_BOARD;";
	public static final String BOARD_LIST = "select * from JB_BOARD order by seq desc limit ?, 10;";
	public static final String BOARD_WRITE = "insert into JB_BOARD set cate=?, title=?, content=?, uid=?, regip=?, rdate=now();";
	public static final String BOARD_VIEW = "select * from JB_BOARD where seq=?";

}
