package com.rhy.note.ffmpeg;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.ffmpeg.global.swresample;
import org.bytedeco.javacv.*;

public class RTMPStream {
    public static void main(String[] args) throws FrameGrabber.Exception, FrameRecorder.Exception {
        FFmpegLogCallback.set();
        String inputUrl = "D:\\ffmpeg\\bin\\DJI_20220805110354_0003_Z.MP4";  // 本地MP4文件路径
        String outputUrl = "rtmp://12.30.4.137:10086/live/javaffmpeg";  // RTMP推流地址

        //设置FFmpeg日志级别
        avutil.av_log_set_level(avutil.AV_LOG_INFO);
        FFmpegLogCallback.set();

        //以文件路径的方式传入视频，当然也支持以流的方式传入
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputUrl);
        //开始捕获视频流
        grabber.start();
        AVFormatContext avFormatContext = grabber.getFormatContext();
        //获取视频时长
        //long duration = avFormatContext.duration();
        //检查文件是否媒体流(视频流、音频流)
        if (avFormatContext.nb_streams() == 0) {
            //表明没有媒体流
            return;
        }

        //用于将捕获到的视频流转换为输出URL的mp4格式。
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputUrl, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
        recorder.setFormat("flv");
        // 设置视频比特率
        recorder.setVideoBitrate(grabber.getVideoBitrate());
        // 设置帧率
        recorder.setFrameRate(grabber.getVideoFrameRate());
        // 设置关键帧间隔
        recorder.setGopSize((int) grabber.getVideoFrameRate());
        // CRF 是一种用于控制视频/音频质量的参数，它允许在保持目标质量的同时动态地调整比特率。较低的CRF值表示更高的质量，但也可能导致较大的文件大小
        recorder.setAudioOption("crf", "23");

        Frame frame;
        //设置音频编码为AAC
        recorder.setAudioChannels(grabber.getAudioChannels());
        recorder.setAudioBitrate(grabber.getAudioBitrate());
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        //将解码后的帧记录到输出文件中
        //recorder.start通常用于处理已经解码成图像的视频数据
        recorder.start();
        while ((frame = grabber.grab()) != null) {
            recorder.record(frame);
        }
        recorder.close();
        grabber.close();
    }
}
