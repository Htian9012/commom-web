package com.lzh.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MscPermExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MscPermExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPermNameIsNull() {
            addCriterion("perm_name is null");
            return (Criteria) this;
        }

        public Criteria andPermNameIsNotNull() {
            addCriterion("perm_name is not null");
            return (Criteria) this;
        }

        public Criteria andPermNameEqualTo(String value) {
            addCriterion("perm_name =", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameNotEqualTo(String value) {
            addCriterion("perm_name <>", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameGreaterThan(String value) {
            addCriterion("perm_name >", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameGreaterThanOrEqualTo(String value) {
            addCriterion("perm_name >=", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameLessThan(String value) {
            addCriterion("perm_name <", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameLessThanOrEqualTo(String value) {
            addCriterion("perm_name <=", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameLike(String value) {
            addCriterion("perm_name like", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameNotLike(String value) {
            addCriterion("perm_name not like", value, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameIn(List<String> values) {
            addCriterion("perm_name in", values, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameNotIn(List<String> values) {
            addCriterion("perm_name not in", values, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameBetween(String value1, String value2) {
            addCriterion("perm_name between", value1, value2, "permName");
            return (Criteria) this;
        }

        public Criteria andPermNameNotBetween(String value1, String value2) {
            addCriterion("perm_name not between", value1, value2, "permName");
            return (Criteria) this;
        }

        public Criteria andPermDescIsNull() {
            addCriterion("perm_desc is null");
            return (Criteria) this;
        }

        public Criteria andPermDescIsNotNull() {
            addCriterion("perm_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPermDescEqualTo(String value) {
            addCriterion("perm_desc =", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescNotEqualTo(String value) {
            addCriterion("perm_desc <>", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescGreaterThan(String value) {
            addCriterion("perm_desc >", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescGreaterThanOrEqualTo(String value) {
            addCriterion("perm_desc >=", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescLessThan(String value) {
            addCriterion("perm_desc <", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescLessThanOrEqualTo(String value) {
            addCriterion("perm_desc <=", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescLike(String value) {
            addCriterion("perm_desc like", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescNotLike(String value) {
            addCriterion("perm_desc not like", value, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescIn(List<String> values) {
            addCriterion("perm_desc in", values, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescNotIn(List<String> values) {
            addCriterion("perm_desc not in", values, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescBetween(String value1, String value2) {
            addCriterion("perm_desc between", value1, value2, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermDescNotBetween(String value1, String value2) {
            addCriterion("perm_desc not between", value1, value2, "permDesc");
            return (Criteria) this;
        }

        public Criteria andPermValueIsNull() {
            addCriterion("perm_value is null");
            return (Criteria) this;
        }

        public Criteria andPermValueIsNotNull() {
            addCriterion("perm_value is not null");
            return (Criteria) this;
        }

        public Criteria andPermValueEqualTo(String value) {
            addCriterion("perm_value =", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueNotEqualTo(String value) {
            addCriterion("perm_value <>", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueGreaterThan(String value) {
            addCriterion("perm_value >", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueGreaterThanOrEqualTo(String value) {
            addCriterion("perm_value >=", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueLessThan(String value) {
            addCriterion("perm_value <", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueLessThanOrEqualTo(String value) {
            addCriterion("perm_value <=", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueLike(String value) {
            addCriterion("perm_value like", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueNotLike(String value) {
            addCriterion("perm_value not like", value, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueIn(List<String> values) {
            addCriterion("perm_value in", values, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueNotIn(List<String> values) {
            addCriterion("perm_value not in", values, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueBetween(String value1, String value2) {
            addCriterion("perm_value between", value1, value2, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermValueNotBetween(String value1, String value2) {
            addCriterion("perm_value not between", value1, value2, "permValue");
            return (Criteria) this;
        }

        public Criteria andPermLevelIsNull() {
            addCriterion("perm_level is null");
            return (Criteria) this;
        }

        public Criteria andPermLevelIsNotNull() {
            addCriterion("perm_level is not null");
            return (Criteria) this;
        }

        public Criteria andPermLevelEqualTo(Integer value) {
            addCriterion("perm_level =", value, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelNotEqualTo(Integer value) {
            addCriterion("perm_level <>", value, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelGreaterThan(Integer value) {
            addCriterion("perm_level >", value, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("perm_level >=", value, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelLessThan(Integer value) {
            addCriterion("perm_level <", value, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelLessThanOrEqualTo(Integer value) {
            addCriterion("perm_level <=", value, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelIn(List<Integer> values) {
            addCriterion("perm_level in", values, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelNotIn(List<Integer> values) {
            addCriterion("perm_level not in", values, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelBetween(Integer value1, Integer value2) {
            addCriterion("perm_level between", value1, value2, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("perm_level not between", value1, value2, "permLevel");
            return (Criteria) this;
        }

        public Criteria andPermOrderIsNull() {
            addCriterion("perm_order is null");
            return (Criteria) this;
        }

        public Criteria andPermOrderIsNotNull() {
            addCriterion("perm_order is not null");
            return (Criteria) this;
        }

        public Criteria andPermOrderEqualTo(Integer value) {
            addCriterion("perm_order =", value, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderNotEqualTo(Integer value) {
            addCriterion("perm_order <>", value, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderGreaterThan(Integer value) {
            addCriterion("perm_order >", value, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("perm_order >=", value, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderLessThan(Integer value) {
            addCriterion("perm_order <", value, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderLessThanOrEqualTo(Integer value) {
            addCriterion("perm_order <=", value, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderIn(List<Integer> values) {
            addCriterion("perm_order in", values, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderNotIn(List<Integer> values) {
            addCriterion("perm_order not in", values, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderBetween(Integer value1, Integer value2) {
            addCriterion("perm_order between", value1, value2, "permOrder");
            return (Criteria) this;
        }

        public Criteria andPermOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("perm_order not between", value1, value2, "permOrder");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNull() {
            addCriterion("modify_date is null");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("modify_date is not null");
            return (Criteria) this;
        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("modify_date =", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("modify_date <>", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("modify_date >", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_date >=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("modify_date <", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("modify_date <=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIn(List<Date> values) {
            addCriterion("modify_date in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotIn(List<Date> values) {
            addCriterion("modify_date not in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("modify_date between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("modify_date not between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}