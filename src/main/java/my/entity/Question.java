package my.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Question {
    /**
     * Table "question"
     * ID entry in tables "candidate_answer", "question_addition" and "question_course_maps"
     */
    private int id;
    /**
     * Table "question"
     * Description question
     */
    private String caption;
    /**
     * Table "question"
     * Mandatory question
     */
    private boolean isMandatory;
    /**
     * Tables "question" and "type"
     * ID entry in tables "type"
     */
    private int typeId;
    /**
     * Table "type"
     * Value type by type_id
     */
    private String typeValue;
    /**
     * Table "question_addition"
     * ID with question_addition
     */
    private int additionId;
    /**
     * Table "question_addition"
     * Value addition by question_id and addition_id
     */
    private List<String> additionValueArray;
    /**
     * Table "question_course_maps"
     * ID entry in tables "course_setting"
     */
    private int courseId;
    /**
     * Table "question_course_maps"
     * Number order with question_id and course_id in profile
     */
    private int orderNumber;

    public Question() {
    }

    public Question(int id, String caption, boolean isMandatory, int typeId, String typeValue, int additionId,
                    List<String> additionValueArray, int courseId, int orderNumber) {
        this.id = id;
        this.caption = caption;
        this.isMandatory = isMandatory;
        this.typeId = typeId;
        this.typeValue = typeValue;
        this.additionId = additionId;
        this.additionValueArray = additionValueArray;
        this.courseId = courseId;
        this.orderNumber = orderNumber;
    }

    public Question(String caption, boolean isMandatory, int typeId, String typeValue,
                    List<String> additionValueArray, int courseId, int additionId, int orderNumber) {
        this.caption = caption;
        this.isMandatory = isMandatory;
        this.typeId = typeId;
        this.typeValue = typeValue;
        this.additionValueArray = additionValueArray;
        this.courseId = courseId;
        this.additionId = additionId;
        this.orderNumber = orderNumber;
    }

    public Question(int id, String caption, boolean isMandatory, int typeId, String typeValue,
                    List<String> additionValueArray, int courseId, int orderNumber) {
        this.id = id;
        this.caption = caption;
        this.isMandatory = isMandatory;
        this.typeId = typeId;
        this.typeValue = typeValue;
        this.additionValueArray = additionValueArray;
        this.courseId = courseId;
        this.orderNumber = orderNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public int getAdditionId() {
        return additionId;
    }

    public void setAdditionId(int additionId) {
        this.additionId = additionId;
    }

    public List<String> getAdditionValueArray() {
        return additionValueArray;
    }

    public void setAdditionValueArray(List<String> additionValueArray) {
        this.additionValueArray = additionValueArray;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", isMandatory=" + isMandatory +
                ", typeId=" + typeId +
                ", typeValue='" + typeValue + '\'' +
                ", additionId=" + additionId +
                ", additionValueArray=" + additionValueArray +
                ", courseId=" + courseId +
                ", orderNumber=" + orderNumber +
                '}';
    }
}