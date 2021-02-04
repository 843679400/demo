package com.example.demo.modules.work;

import javax.xml.bind.Marshaller;

public class Main {
    public static void main(String[] args) {
        Transcript transcript = new Transcript();

        new TeacherObserver(transcript);
        new ParentsObserver(transcript);
        new ClassmateObserver(transcript);

        transcript.setTranscript("王明",100,"数学");
    }
}
