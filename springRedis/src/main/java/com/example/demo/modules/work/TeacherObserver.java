package com.example.demo.modules.work;

/**
 * 教师观察者
 */
public class TeacherObserver extends Observer {

    public TeacherObserver() {
    }

    public TeacherObserver(Transcript transcript) {
        this.transcript = transcript;
        this.transcript.attach(this);
    }


    @Override
    public void read() {
        if(this.transcript.getFraction()>80){
            System.out.println(this.transcript.getName()+"的"+
                    this.transcript.getSubject()+"成绩考了"+
                    this.transcript.getFraction()+
                    "分,备注:成绩不错");
        }else {
            System.out.println(this.transcript.getName()+"的"+
                    this.transcript.getSubject()+"成绩考了"+
                    this.transcript.getFraction()+
                    "分,备注:成绩不理想");
        }
    }
}
