#include "com_example_app_yarabisahl_OpencvClass.h"
JNIEXPORT void JNICALL Java_com_example_app_yarabisahl_OpencvClass_faceDetection
(JNIEnv *, jclass, jlong addrRgba){

Mat& frame = *(Mat*)addrRgba;

detect(frame);
}
JNIEXPORT void JNICALL Java_com_example_app_yarabisahl_OpencvClass_fisher
(JNIEnv *, jclass, jlong ) {
fisherFaceTrainer();
}
JNIEXPORT void JNICALL
Java_com_example_app_yarabisahl_OpencvClass_RecogniseFace(JNIEnv *env, jclass type,jlong addrRgba) {

    Mat& frame = *(Mat*)addrRgba;
    FaceRecognition(frame);
}

JNIEXPORT void JNICALL
Java_com_example_app_yarabisahl_OpencvClass_AddNewPerson(JNIEnv *env, jclass type) {

AddSomeOne();
}
JNIEXPORT jint JNICALL
Java_com_example_app_yarabisahl_OpencvClass_lab1(JNIEnv *env, jclass type) {

     getLab1();

}
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab2(JNIEnv *env, jclass type)
{
    getLab2();
}
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab3(JNIEnv *env, jclass type)
{
    getLab3();
}
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab4(JNIEnv *env, jclass type)
{
    getLab4();
}
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab5(JNIEnv *env, jclass type)
{
    getLab5();
}
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab6(JNIEnv *env, jclass type)
{
    getLab6();
}
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab7(JNIEnv *env, jclass type)
{
    getLab7();
}
static Mat norm_0_255(InputArray _src) {
    Mat src = _src.getMat();
    // Create and return normalized image:
    Mat dst;
    switch(src.channels()) {
        case 1:
            cv::normalize(_src, dst, 0, 255, NORM_MINMAX, CV_8UC1);
            break;
        case 3:
            cv::normalize(_src, dst, 0, 255, NORM_MINMAX, CV_8UC3);
            break;
        default:
            src.copyTo(dst);
            break;
    }
    return dst;
}
void detect(Mat& frame) {

    String face_cascade_name = "/storage/emulated/0/data/haarcascade_frontalface_alt.xml";
    CascadeClassifier face_cascade;
    if (!face_cascade.load(face_cascade_name)){

        return ;
    }

    std::vector<Rect> faces;
    Mat frame_gray;
    cvtColor(frame, frame_gray, CV_BGR2GRAY);
    equalizeHist(frame_gray, frame_gray);
    //-- Detect faces
    face_cascade.detectMultiScale(frame_gray, faces, 1.1, 2, 0 | CV_HAAR_SCALE_IMAGE,Size(30, 30));
    size_t i;
    for ( i = 0; i < faces.size(); i++) {

        Mat faceROI = frame_gray(faces[i]);
        int x = faces[i].x;
        int y = faces[i].y;
        int h = 0.05 * y + faces[i].height;
        int w = faces[i].width;
        rectangle(frame, Point(x, y), Point(x + w, y + h), Scalar(102, 204, 255), 1, 4, 0);
        //rectangle(frame, faces[i], CV_RGB(0, 255, 0), 1);
        k++;
        if (k <= 4)
        {
            imwrite("/storage/emulated/0/Img/" + tostring(imgi) + "_" + tostring(k) +
                    ".jpg", faceROI);
            fstream fichier("/storage/emulated/0/Files/Trainer.txt");
            fichier.seekp(0,ios::end);//On se rend à la fin du fichier afin de ne pas effacer le contenu déjà présent
            fichier << "/storage/emulated/0/Img/"<<tostring(imgi)<<"_"<<tostring(k)<<".jpg"<<" "<<tostring(imgi)<<" "<<loadFromFile()<<"\n";
            fichier.close();

        }
    }


}
void AddSomeOne()
{
    ifstream F("/storage/emulated/0/Files/Trainer.txt");
    string path;
    int Label;
    int R;
    while(F >> path >> Label >> NameX)
    {
        cout << path << ", " << Label << ", " << NameX <<endl;

            R = Label;
    }
    imgi = R+1;
    k=0;
}
String tostring(int a)
{
    stringstream ss;
    ss << a;
    return  ss.str();
}

static void dbread(const string& filename, vector<Mat>& images, vector<int>& labels,vector<String>& Names, char separator = ' '){
    std::ifstream file(filename.c_str(), ifstream::in);

    if (!file){
        string error = "no valid input file";
        CV_Error(CV_StsBadArg, error);
    }

    string line, path,label,Name;
    while (getline(file, line))
    {
        stringstream liness(line);
        getline(liness, path, separator);
        getline(liness, label,separator);
        getline(liness,Name);
        if (!path.empty() && !label.empty() && !Name.empty()){
            images.push_back(imread(path,0));
            labels.push_back(atoi(label.c_str()));
            Names.push_back(Name);
        }
    }
}

String loadFromFile()
{
    ifstream F ;
    F.open("/storage/emulated/0/Files/F.txt");
    string mot;
    F >> mot;
    return mot;
}

String DetectName(int lab)
{
    ifstream F("/storage/emulated/0/Files/Trainer.txt");
    string path;
    int Label;
    String R;
    while(F >> path >> Label >> NameX)
    {
        cout << path << ", " << Label << ", " << NameX <<endl;
        if(Label == lab )
            R = NameX;
    }
    return R;
}
void fisherFaceTrainer(){
    /*in this two vector we put the images and labes for training*/
    vector<Mat> images;
    vector<int> labels;
    vector<String> Names;
    try{
        string filename = "/storage/emulated/0/Files/Trainer.txt";
        dbread(filename, images, labels,Names);

    }
    catch (Exception& e){
        exit(1);
    }

    Ptr<FaceRecognizer> model =  createLBPHFaceRecognizer();

    model->train(images, labels);

    int height = images[0].rows;

    model->save("/storage/emulated/0/FiSherFile/FisherFace.yml");
}

void  FaceRecognition(Mat& frame){

    // cout << "start recognizing..." << endl;

    //load pre-trained data sets
    Ptr<FaceRecognizer>  model = createLBPHFaceRecognizer();
    model->load("/storage/emulated/0/FiSherFile/FisherFace.yml");

    Mat testSample = imread("/storage/emulated/0/ImagesFacesReco/1_13.jpg", 0);

    int img_width = testSample.cols;
    int img_height = testSample.rows;


    //lbpcascades/lbpcascade_frontalface.xml
    string classifier = "/storage/emulated/0/data/haarcascade_frontalface_alt.xml";

    CascadeClassifier face_cascade;
    // string window = "Capture - face detection";

    if (!face_cascade.load(classifier)){

        return ;
    }


    long count = 0;

    vector<Rect> faces;

    Mat graySacleFrame;
    //Mat original;

    count = count + 1;//count frames;



    //clone from original frame
    frame = frame.clone();

    //convert image to gray scale and equalize
    cvtColor(frame, graySacleFrame, CV_BGR2GRAY);

    equalizeHist(graySacleFrame, graySacleFrame);

    //detect face in gray image
    face_cascade.detectMultiScale(graySacleFrame, faces, 1.1, 3, 0, cv::Size(90, 90));

    //number of faces detected
    //cout << faces.size() << " faces detected" << endl;
    std::string frameset = tostring(count);
    std::string faceset = tostring(faces.size());

    int width = 0, height = 0;
int i=0;
    for ( i = 0; i < faces.size(); i++)
    {
        //region of interest
        Rect face_i = faces[i];

        //crop the roi from grya image
        Mat face = graySacleFrame(face_i);

        //resizing the cropped image to suit to database image sizes
        Mat face_resized;
        cv::resize(face, face_resized, Size(img_width, img_height), 1.0, 1.0, INTER_CUBIC);

        //recognizing what faces detected
        label = 10;
        double confidence = 0;
        model->predict(face_resized, label, confidence);


        //drawing green rectagle in recognize face
        rectangle(frame, face_i, CV_RGB(0, 255, 0), 1);


        int pos_x = std::max(face_i.tl().x - 10, 0);
        int pos_y = std::max(face_i.tl().y - 10, 0);

        //name the person who is in the image
        putText(frame ,DetectName(label), Point(pos_x, pos_y), FONT_HERSHEY_COMPLEX_SMALL, 1.0, CV_RGB(0, 255, 0), 1.0);


    }

    if(label==1)
        a1=label;
    if(label==2)
        a2=label;
    if(label==3)
        a3=label;
    if(label==4)
        a4=label;
    if(label==5)
        a5=label;
    if(label==6)
        a6=label;
    if(label==7)
        a7=label;

}
int getLab1()
{
    return a1;
}
int getLab2()
{
    return a2;
}
int getLab3()
{
    return a3;
}
int getLab4()
{
    return a4;
}
int getLab5()
{
    return a5;
}
int getLab6()
{
    return a6;
}
int getLab7()
{
    return a7;
}