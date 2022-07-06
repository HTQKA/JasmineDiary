package fly.xysimj.jasminediary.entity;

import fly.xysimj.jasminediary.utils.StrUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: JasmineDiary
 * @ClassName Sql
 * @description: 动态创建Sql语句
 * @author: 徐杨顺
 * @create: 2022-07-06 10:39
 * @Version 1.0
 **/
public class Sql {

    private static final String SELECT = "SELECT";
    private static final String FROM = "FROM";
    private static final String WHERE = "WHERE";
    private static final String AND = "AND ";
    private static final String IN = " IN ";

    private static final String INSERT_INTO = "INSERT INTO";
    private static final String VALUES = "VALUES";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE_FROM = "DELETE FROM";
    private static final String COUNT = "COUNT";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String BRACKET_NULL = "(NULL)";

    private static final String SCRIPT_HEAD = "<script>";
    private static final String SCRIPT_END = "</script>";

    private static final String WHERE_HEAD = "<where>";
    private static final String WHERE_END = "</where>";

    private static final String IF_HEAD_LIST_NOT_BLANK = "<if test = \"list != null and list.size() > 0\">";
    private static final String IF_HEAD_LIST_BLANK = "<if test = \"list == null or list.size() == 0\">";
    private static final String IF_HEAD_PREFIX = "<if test = \"";
    private static final String IF_HEAD_SUFFIX = " != null\">";
    private static final String IF_END = "</if>";

    private static final String SCRIPT_TYPE = "</";

    private static final String TRIM_HEAD_BRACKET = "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">";
    private static final String TRIM_END = "</trim>";

    private static final String SET_HEAD = "<set>";
    private static final String SET_END = "</set>";

    /**
     * foreach 要做foreach的对象，作为入参时，List对象默认用"list"代替作为键，数组对象有"array"代替作为键
     * 在作为入参时可以使用@Param("keyName")来设置键，设置keyName后，list,array将会失效。
     * 除了入参这种情况外，还有一种作为参数对象的某个字段的时候。举个例子：如果User有属性List ids。入参是User对象，那么这个collection = "ids"
     */
    private static final String FOREACH_HEAD_LIST = "<foreach item = \"item\" collection = \"list\" separator = \",\" index = \"index\">";
    private static final String FOREACH_HEAD_BRACKET_LIST = "<foreach item = \"item\" collection = \"list\" separator = \",\" open=\"(\" close=\")\" index = \"index\">";

    private static final String FOREACH_ITEM = "#{item}";
    private static final String FOREACH_END = "</foreach>";

    private static final String EQUALS = " = ";
    private static final String COMMA = ",";
    private static final String COMMA_CRLF_TAB = ",\n\t";
    private static final String COMMA_CRLF_TAB_TAB = ",\n\t\t";

    private final StringBuilder sql;

    public Sql() {
        this.sql = new StringBuilder();
    }

    public Sql sql() {
        return this;
    }

    public Sql sql(String str) {
        sql.append(str);
        return this;
    }

    public Sql crlf() {
        return sql("\n");
    }

    public Sql tab() {
        return sql("\t");
    }

    public Sql select(String... fields) {
        List<String> columns = Arrays.stream(fields).map(StrUtil::camelToUnderscore).collect(Collectors.toList());
        sql(SELECT).crlf();
        tab().sql(String.join(COMMA_CRLF_TAB, columns)).crlf();
        return this;
    }

    public Sql selectCount(String field) {
        sql(SELECT).crlf();
        tab().sql(COUNT).sql(OPEN_BRACKET).sql(field).sql(CLOSE_BRACKET).crlf();
        return this;
    }

    public Sql from(String tableName) {
        sql(FROM).crlf();
        tab().sql(tableName).crlf();
        return this;
    }
    public Sql where(String... fields) {
        List<String> conditions = Arrays.stream(fields).map(this::ifAndColumnFieldRel).collect(Collectors.toList());
        sql(WHERE_HEAD).crlf();
        sql(String.join("", conditions));
        sql(WHERE_END).crlf();
        return this;
    }

    public Sql whereIn(String field) {
        sql(WHERE).crlf();
        foreachIn(field);
        return this;
    }

    /**
     * 插入不为null的字段
     */
    public Sql insertInto(String tableName, String... fields) {
        String[] ifColumns = Arrays.stream(fields).map(this::ifColumnComma).toArray(String[]::new);
        sql(INSERT_INTO).crlf();
        tab().sql(tableName).crlf();
        tab().trimHeadBracket();
        sql(String.join("", ifColumns));
        tab().trimEnd();
        return this;
    }

    public Sql insertIntoBatch(String tableName, String... fields) {
        String[] columns = Arrays.stream(fields).map(StrUtil::camelToUnderscore).toArray(String[]::new);
        sql(INSERT_INTO).crlf();
        tab().sql(tableName).sql(OPEN_BRACKET).crlf();
        tab().tab().sql(String.join(COMMA_CRLF_TAB_TAB, columns)).crlf();
        tab().sql(CLOSE_BRACKET).crlf();
        return this;
    }

    public Sql values(String... fields) {
        String[] ifFields = Arrays.stream(fields).map(this::ifHashFieldComma).toArray(String[]::new);
        sql(VALUES).crlf();
        tab().trimHeadBracket();
        sql(String.join("", ifFields));
        tab().trimEnd();
        return this;
    }

    public Sql valuesBatch(String... fields) {
        String[] hashListFields = Arrays.stream(fields).map(this::hashListField).toArray(String[]::new);
        sql(VALUES).crlf();
        tab().foreachHeadList();
        tab().trimHeadBracket();
        tab().tab().sql(String.join(COMMA_CRLF_TAB_TAB, hashListFields)).crlf();
        tab().trimEnd();
        tab().foreachEnd();
        return this;
    }


    public Sql update(String tableName) {
        sql(UPDATE).crlf();
        tab().sql(tableName).crlf();
        return this;
    }

    public Sql set(String... fields) {
        List<String> conditions = Arrays.stream(fields).map(this::ifColumnFieldRel).collect(Collectors.toList());
        sql(SET_HEAD).crlf();
        tab().sql(String.join(COMMA_CRLF_TAB, conditions)).crlf();
        sql(SET_END).crlf();
        return this;
    }

    public Sql deleteFrom(String tableName) {
        sql(DELETE_FROM).crlf();
        tab().sql(tableName).crlf();
        return this;
    }


    public Sql in() {
        return sql(IN);
    }

    public Sql ifHeadListNotBlank() {
        return sql(IF_HEAD_LIST_NOT_BLANK).crlf();
    }

    public Sql ifHeadListBlank() {
        return sql(IF_HEAD_LIST_BLANK).crlf();
    }

    public Sql ifHead(String field) {
        return sql(IF_HEAD_PREFIX).sql(field).sql(IF_HEAD_SUFFIX).crlf();
    }

    public Sql ifEnd() {
        return sql(IF_END).crlf();
    }

    public Sql bracketNull() {
        return sql(BRACKET_NULL).crlf();
    }

    public Sql foreachHeadBracketList() {
        return sql(FOREACH_HEAD_BRACKET_LIST).crlf();
    }

    public Sql foreachHeadList() {
        return sql(FOREACH_HEAD_LIST).crlf();
    }

    public Sql foreachItem() {
        return sql(FOREACH_ITEM).crlf();
    }

    public Sql foreachEnd() {
        return sql(FOREACH_END).crlf();
    }

    public Sql trimHeadBracket() {
        return sql(TRIM_HEAD_BRACKET).crlf();
    }

    public Sql trimEnd() {
        return sql(TRIM_END).crlf();
    }

    public Sql foreachIn(String field) {
        tab().sql(StrUtil.camelToUnderscore(field)).in().crlf();
        tab().ifHeadListNotBlank();
        tab().tab().foreachHeadBracketList();
        tab().tab().tab().foreachItem();
        tab().tab().foreachEnd();
        tab().ifEnd();
        tab().ifHeadListBlank();
        tab().tab().bracketNull();
        tab().ifEnd();
        return this;
    }

    @Override
    public String toString() {
        return sql.toString();
    }

    public String build() {
        if (sql.toString().contains(SCRIPT_TYPE)) {
            return SCRIPT_HEAD + "\n" + sql.toString() + SCRIPT_END;
        }
        return sql.toString();
    }

    private String columnFieldRel(String field, String rel) {
        return StrUtil.camelToUnderscore(field) + " " + rel + " " + hashField(field);
    }

    private String columnFieldRel(String field) {
        return columnFieldRel(field, EQUALS);
    }

    private String andColumnFieldRel(String field) {
        return andColumnFieldRel(field, EQUALS);
    }

    private String andColumnFieldRel(String field, String rel) {
        return AND + columnFieldRel(field, rel);
    }

    private String columnFieldRelComma(String field) {
        return columnFieldRelComma(field, EQUALS);
    }

    private String columnFieldRelComma(String field, String rel) {
        return columnFieldRel(field, rel) + COMMA;
    }

    public String ifColumnComma(String field) {
        return ifScript(field, StrUtil.camelToUnderscore(field) + COMMA);
    }

    public String ifHashFieldComma(String field) {
        return ifScript(field, hashField(field) + COMMA);
    }

    public String ifColumnFieldRel(String field) {
        return ifScript(field, columnFieldRel(field));
    }

    public String ifAndColumnFieldRel(String field) {
        return ifScript(field, andColumnFieldRel(field));
    }

    public String ifColumnFieldRelComma(String field) {
        return ifScript(field, columnFieldRelComma(field));
    }

    public String ifScript(String field, String express) {
        return new Sql()
                .tab().ifHead(field)
                .tab().tab().sql(express).crlf()
                .tab().ifEnd().toString();
    }

    private String hashField(String field) {
        return "#{" + field + "}";
    }

    private String hashListField(String field) {
        return "#{item." + field + "}";
    }
}
