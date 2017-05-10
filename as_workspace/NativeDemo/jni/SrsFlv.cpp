//
// Created by caojin on 2017/3/26.
//
#include <iostream>
#include <vector>
#include <cstring>
#include "net_ossrs_yasea_flv_SrsFlv.h"
#include <android/log.h>
#define TAG "SrsFlv"
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

using namespace std;

typedef unsigned char BYTE;

#ifdef __cplusplus
extern "C" {
#endif

class ByteBuffer {
private:
    BYTE *pArray;
    int positionInter;
    int limitInter;
    int capacityInter;
    ByteBuffer(BYTE *pArray, int length) {
        this->pArray = pArray;
        this->positionInter = 0;
        this->limitInter = length;
        this->capacityInter = length;
    }

    ByteBuffer(int capacity) {
        this->pArray = new BYTE[capacity];
        this->positionInter = 0;
        this->limitInter = capacity;
        this->capacityInter = capacity;
    }
public:
    ~ByteBuffer() {
        this->pArray = NULL;
        this->positionInter = 0;
        this->limitInter = 0;
        this->capacityInter = 0;
    }
    ByteBuffer() {
        this->pArray = NULL;
        this->positionInter = 0;
        this->limitInter = 0;
        this->capacityInter = 0;
    }

    static ByteBuffer allocate(int capacity) {
        return ByteBuffer(capacity);
    }

    static ByteBuffer wrap(BYTE *pArray, int length) {
        return ByteBuffer(pArray, length);
    }
    bool operator==(ByteBuffer* ob) {
        if (this == ob)
            return true;
        ByteBuffer that = *ob;
        if (this->remaining() != that.remaining())
            return false;
        int p = this->position();
        for (int i = this->limit() - 1, j = that.limit() - 1; i >= p; i--, j--)
            if (this->get(i) != that.get(j))
                return false;
        return true;
    }

    BYTE* array() {
        return this->pArray;
    }

    ByteBuffer get(BYTE *pDstArray, int length) {
        return get(pDstArray, 0, length);
    }

    ByteBuffer get(BYTE *pDstArray, int offset, int length) {
        if (remaining() < length) {
            LOGD("not enough data left");
            throw "not enough data left";
        }
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            *(pDstArray + i) = get();
        }
        return *this;
    }

    BYTE get() {
        if (this->positionInter < this->limitInter) {
            return *(pArray + (this->positionInter++));
        } else {
            LOGD("reach limit");
            throw "reach limit";
        }
    }

    int getInt() {
        int size = sizeof(int);
        if (this->positionInter + size > this->limitInter) {
            LOGD("no int value left");
            throw "no int value left";
        }
        int result = *(pArray + this->positionInter);
        this->positionInter += size;
        return result;
    }

    short getShort() {
        int size = sizeof(short);
        if (this->positionInter + size > this->limitInter) {
            LOGD("no int value left");
            throw "no int value left";
        }
        int result = *(pArray + this->positionInter);
        this->positionInter += size;
        return result;
    }

    BYTE get(int index) {
        if (index > this->limitInter) {
            LOGD("index > limit");
            throw "index > limit";
        }
        return *(pArray + index);
    }

    ByteBuffer put(BYTE item) {
        if (this->positionInter == this->limitInter) {
            LOGD("not enough space");
            throw "not enough space";
        }
        *(pArray + this->positionInter++) = item;
        return *this;
    }

    ByteBuffer putInt(int item) {
        int size = sizeof(item);
        if (this->positionInter + size > this->limitInter) {
            LOGD("not enough space");
            throw "not enough space";
        }
        *(pArray + this->positionInter) = item;
        this->positionInter += size;
        return *this;
    }

    ByteBuffer putShort(short item) {
        int size = sizeof(item);
        if (this->positionInter + size > this->limitInter) {
            LOGD("not enough space");
            throw "not enough space";
        }
        *(pArray + this->positionInter) = item;
        this->positionInter += size;
        return *this;
    }

    ByteBuffer put(BYTE *pSrsArray, int length) {
        int size = length;
        if (this->positionInter + size > this->limitInter) {
            LOGD("not enough space");
            throw "not enough space";
        }
        for (int i = 0; i < length; i++) {
            put(*(pSrsArray + i));
        }
        return *this;
    }

    int position() {
        return this->positionInter;
    }

    void position(int position) {
        this->positionInter = position;
    }

    int limit() {
        return this->limitInter;
    }

    void limit(int limit) {
        this->limitInter = limit;
    }

    int capacity() {
        return this->capacityInter;
    }

    int remaining() {
        return this->limitInter - this->positionInter;
    }

    ByteBuffer rewind() {
        this->positionInter = 0;
        return *this;
    }

    ByteBuffer clear() {
        this->positionInter = 0;
        this->limitInter = this->capacityInter;
        return *this;
    }

    ByteBuffer slice() {
        return ByteBuffer(this->pArray + this->positionInter, remaining());
    }

    ByteBuffer duplicate() {
        ByteBuffer result = ByteBuffer(this->pArray, this->capacityInter);
        result.position(this->positionInter);
        result.limit(this->limitInter);
        return result;
    }
};

/**
 * Table 7-1 – NAL unit type codes, syntax element categories, and NAL unit type classes
 * H.264-AVC-ISO_IEC_14496-10-2012.pdf, page 83.
 */
enum SrsAvcNaluType {
    // Unspecified
            Reserved = 0,
    // Coded slice of a non-IDR picture slice_layer_without_partitioning_rbsp( )
            NonIDR = 1,
    // Coded slice data partition A slice_data_partition_a_layer_rbsp( )
            DataPartitionA = 2,
    // Coded slice data partition B slice_data_partition_b_layer_rbsp( )
            DataPartitionB = 3,
    // Coded slice data partition C slice_data_partition_c_layer_rbsp( )
            DataPartitionC = 4,
    // Coded slice of an IDR picture slice_layer_without_partitioning_rbsp( )
            IDR = 5,
    // Supplemental enhancement information (SEI) sei_rbsp( )
            SEI = 6,
    // Sequence parameter set seq_parameter_set_rbsp( )
            SPS = 7,
    // Picture parameter set pic_parameter_set_rbsp( )
            PPS = 8,
    // Access unit delimiter access_unit_delimiter_rbsp( )
            AccessUnitDelimiter = 9,
    // End of sequence end_of_seq_rbsp( )
            EOSequence = 10,
    // End of stream end_of_stream_rbsp( )
            EOStream = 11,
    // Filler data filler_data_rbsp( )
            FilterData = 12,
    // Sequence parameter set extension seq_parameter_set_extension_rbsp( )
            SPSExt = 13,
    // Prefix NAL unit prefix_nal_unit_rbsp( )
            PrefixNALU = 14,
    // Subset sequence parameter set subset_seq_parameter_set_rbsp( )
            SubsetSPS = 15,
    // Coded slice of an auxiliary coded picture without partitioning slice_layer_without_partitioning_rbsp( )
            LayerWithoutPartition = 19,
    // Coded slice extension slice_layer_extension_rbsp( )
            CodedSliceExt = 20
};

// E.4.3.1 VIDEODATA
// Frame Type UB [4]
// Type of video frame. The following values are defined:
//     1 = key frame (for AVC, a seekable frame)
//     2 = inter frame (for AVC, a non-seekable frame)
//     3 = disposable inter frame (H.263 only)
//     4 = generated key frame (reserved for server use only)
//     5 = video Infotem1/command frame
enum SrsCodecVideoAVCFrame {
    // set to the zero to reserved, for array map.
    // todo change to Reserved2 from Reserved in Java
            Reserved2 = 0,
    Reserved1 = 6,
    KeyFrame = 1,
    InterFrame = 2,
    DisposableInterFrame = 3,
    GeneratedKeyFrame = 4,
    VideoInfoFrame = 5
};

// E.4.3.1 VIDEODATA
// CodecID UB [4]
// Codec Identifier. The following values are defined:
//     2 = Sorenson H.263
//     3 = Screen video
//     4 = On2 VP6
//     5 = On2 VP6 with alpha channel
//     6 = Screen video version 2
//     7 = AVC
enum SrsCodecVideo {
    // set to the zero to reserved, for array map.
            Reserved10 = 0,
    Reserved11 = 1,
    Reserved12 = 9,

    // for user to disable video, for example, use pure audio hls.
            Disabled = 8,

    SorensonH263 = 2,
    ScreenVideo = 3,
    On2VP6 = 4,
    On2VP6WithAlphaChannel = 5,
    ScreenVideoVersion2 = 6,
    AVC = 7
};

/**
 * the FLV/RTMP supported audio sample rate.
 * Sampling rate. The following values are defined:
 * 0 = 5.5 kHz = 5512 Hz
 * 1 = 11 kHz = 11025 Hz
 * 2 = 22 kHz = 22050 Hz
 * 3 = 44 kHz = 44100 Hz
 */
enum SrsCodecAudioSampleRate {
    // set to the max value to reserved, for array map.
            Reserved20 = 4,
    R5512 = 0,
    R11025 = 1,
    R22050 = 2,
    R44100 = 3
};

/**
 * E.4.1 FLV Tag, page 75
 */
enum SrsCodecFlvTag {
    // set to the zero to reserved, for array map.
            Reserved30 = 0,

    // 8 = audio
            Audio = 8,
    // 9 = video
            Video = 9,
    // 18 = script data
            Script = 18
};

// AVCPacketType IF CodecID == 7 UI8
// The following values are defined:
//     0 = AVC sequence header
//     1 = AVC NALU
//     2 = AVC end of sequence (lower level NALU sequence ender is
//         not required or supported)
enum SrsCodecVideoAVCType {
    // set to the max value to reserved, for array map.
            Reserved40 = 3,
    SequenceHeader = 0,
    NALU = 1,
    SequenceHeaderEOF = 2
};

/**
 * the demuxed tag frame.
 */
class SrsFlvFrameBytes {
public:
    ByteBuffer data;
    int size;
};

/**
 * the search result for annexb.
 */
class SrsAnnexbSearch {
public:
    int nb_start_code;
    bool match;
    SrsAnnexbSearch() {
        this->nb_start_code = 0;
        this->match = false;
    }
};

/**
 * utils functions from srs.
 */
class SrsUtils {
public:
    static SrsAnnexbSearch
    srs_avc_startswith_annexb(ByteBuffer* bb, int newOffset, int newSize, long newTimeUs, int newFlags) {
        SrsAnnexbSearch as;
        as.match = false;

        int pos = (*bb).position();
        while (pos < newSize - 3) {
            // not match.
            if ((*bb).get(pos) != 0x00 || (*bb).get(pos + 1) != 0x00) {
                break;
            }

            // match N[00] 00 00 01, where N>=0
            if ((*bb).get(pos + 2) == 0x01) {
                as.match = true;
                as.nb_start_code = pos + 3 - (*bb).position();
                break;
            }

            pos++;
        }

        return as;
    }
};

/**
 * the raw h.264 stream, in annexb.
 */
class SrsRawH264Stream {
public:
    static bool is_sps(SrsFlvFrameBytes* frame) {
        if ((*frame).size < 1) {
            return false;
        }
        return ((*frame).data.get(0) & 0x1f) == SPS;
    }

    static bool is_pps(SrsFlvFrameBytes* frame) {
        if ((*frame).size < 1) {
            return false;
        }
        return ((*frame).data.get(0) & 0x1f) == PPS;
    }

    static SrsFlvFrameBytes mux_ibp_frame(SrsFlvFrameBytes* frame) {
        SrsFlvFrameBytes nalu_header;
        nalu_header.size = 4;
        nalu_header.data = ByteBuffer::allocate(nalu_header.size);

        // 5.3.4.2.1 Syntax, H.264-AVC-ISO_IEC_14496-15.pdf, page 16
        // lengthSizeMinusOne, or NAL_unit_length, always use 4bytes size
        int NAL_unit_length = (*frame).size;

        // mux the avc NALU in "ISO Base Media File Format"
        // from H.264-AVC-ISO_IEC_14496-15.pdf, page 20
        // NALUnitLength
        nalu_header.data.putInt(NAL_unit_length);

        // reset the buffer.
        nalu_header.data.rewind();
        return nalu_header;
    }

    static void mux_sequence_header(ByteBuffer* sps, ByteBuffer* pps, int dts, int pts, vector<SrsFlvFrameBytes>* frames) {
        // 5bytes sps/pps header:
        //      configurationVersion, AVCProfileIndication, profile_compatibility,
        //      AVCLevelIndication, lengthSizeMinusOne
        // 3bytes size of sps:
        //      numOfSequenceParameterSets, sequenceParameterSetLength(2B)
        // Nbytes of sps.
        //      sequenceParameterSetNALUnit
        // 3bytes size of pps:
        //      numOfPictureParameterSets, pictureParameterSetLength
        // Nbytes of pps:
        //      pictureParameterSetNALUnit

        // decode the SPS:
        // @see: 7.3.2.1.1, H.264-AVC-ISO_IEC_14496-10-2012.pdf, page 62
        if (true) {
            SrsFlvFrameBytes hdr;
            hdr.size = 5;
            hdr.data = ByteBuffer::allocate(hdr.size);

            // @see: Annex A Profiles and levels, H.264-AVC-ISO_IEC_14496-10.pdf, page 205
            //      Baseline profile profile_idc is 66(0x42).
            //      Main profile profile_idc is 77(0x4d).
            //      Extended profile profile_idc is 88(0x58).
            BYTE profile_idc = (*sps).get(1);
            //u_int8_t constraint_set = frame[2];
            BYTE level_idc = (*sps).get(3);

            // generate the sps/pps header
            // 5.3.4.2.1 Syntax, H.264-AVC-ISO_IEC_14496-15.pdf, page 16
            // configurationVersion
            hdr.data.put((BYTE) 0x01);
            // AVCProfileIndication
            hdr.data.put(profile_idc);
            // profile_compatibility
            hdr.data.put((BYTE) 0x00);
            // AVCLevelIndication
            hdr.data.put(level_idc);
            // lengthSizeMinusOne, or NAL_unit_length, always use 4bytes size,
            // so we always set it to 0x03.
            hdr.data.put((BYTE) 0x03);

            // reset the buffer.
            hdr.data.rewind();
            (*frames).push_back(hdr);
        }

        // sps
        if (true) {
            SrsFlvFrameBytes sps_hdr;
            sps_hdr.size = 3;
            sps_hdr.data = ByteBuffer::allocate(sps_hdr.size);

            // 5.3.4.2.1 Syntax, H.264-AVC-ISO_IEC_14496-15.pdf, page 16
            // numOfSequenceParameterSets, always 1
            sps_hdr.data.put((BYTE) 0x01);
            // sequenceParameterSetLength
            sps_hdr.data.putShort((short) (*sps).capacity());

            sps_hdr.data.rewind();
            (*frames).push_back(sps_hdr);

            // sequenceParameterSetNALUnit
            SrsFlvFrameBytes sps_bb;
            sps_bb.size = (*sps).capacity();
            sps_bb.data = (*sps).duplicate();
            (*frames).push_back(sps_bb);
        }

        // pps
        if (true) {
            SrsFlvFrameBytes pps_hdr;
            pps_hdr.size = 3;
            pps_hdr.data = ByteBuffer::allocate(pps_hdr.size);

            // 5.3.4.2.1 Syntax, H.264-AVC-ISO_IEC_14496-15.pdf, page 16
            // numOfPictureParameterSets, always 1
            pps_hdr.data.put((BYTE) 0x01);
            // pictureParameterSetLength
            pps_hdr.data.putShort((short) (*pps).capacity());

            pps_hdr.data.rewind();
            (*frames).push_back(pps_hdr);

            // pictureParameterSetNALUnit
            SrsFlvFrameBytes pps_bb;
            pps_bb.size = (*pps).capacity();
            pps_bb.data = (*pps).duplicate();
            (*frames).push_back(pps_bb);
        }
    }

    static SrsFlvFrameBytes
    mux_avc2flv(vector<SrsFlvFrameBytes>* frames, int frame_type, int avc_packet_type, int dts, int pts) {
        SrsFlvFrameBytes flv_tag;

        // for h264 in RTMP video payload, there is 5bytes header:
        //      1bytes, FrameType | CodecID
        //      1bytes, AVCPacketType
        //      3bytes, CompositionTime, the cts.
        // @see: E.4.3 Video Tags, video_file_format_spec_v10_1.pdf, page 78
        flv_tag.size = 5;
        for (int i = 0; i < (*frames).size(); i++) {
            SrsFlvFrameBytes frame = (*frames)[i];
            flv_tag.size += frame.size;
        }

        flv_tag.data = ByteBuffer::allocate(flv_tag.size);

        // @see: E.4.3 Video Tags, video_file_format_spec_v10_1.pdf, page 78
        // Frame Type, Type of video frame.
        // CodecID, Codec Identifier.
        // set the rtmp header
        flv_tag.data.put((BYTE) ((frame_type << 4) | AVC));

        // AVCPacketType
        flv_tag.data.put((BYTE) avc_packet_type);

        // CompositionTime
        // pts = dts + cts, or
        // cts = pts - dts.
        // where cts is the header in rtmp video packet payload header.
        int cts = pts - dts;
        flv_tag.data.put((BYTE) (cts >> 16));
        flv_tag.data.put((BYTE) (cts >> 8));
        flv_tag.data.put((BYTE) cts);

        // h.264 raw data.
        for (int i = 0; i < (*frames).size(); i++) {
            SrsFlvFrameBytes frame = (*frames)[i];
            BYTE frame_bytes[frame.size];
            frame.data.get(frame_bytes, frame.size);
            flv_tag.data.put(frame_bytes, frame.size);
        }

        // reset the buffer.
        flv_tag.data.rewind();
        return flv_tag;
    }

    static SrsFlvFrameBytes annexb_demux(ByteBuffer* bb, int newOffset, int newSize, long newTimeUs, int newFlags) {
        SrsFlvFrameBytes tbb;

        while ((*bb).position() < newSize) {
            // each frame must prefixed by annexb format.
            // about annexb, @see H.264-AVC-ISO_IEC_14496-10.pdf, page 211.
            SrsAnnexbSearch tbbsc = SrsUtils::srs_avc_startswith_annexb(bb, newOffset, newSize, newTimeUs, newFlags);
            if (!tbbsc.match || tbbsc.nb_start_code < 3) {
                (*bb).position((*bb).position() + ((*bb).remaining() < 16 ? (*bb).remaining() : 16));
                LOGD("annexb not match.");
                //SrsFlvMuxer.srs_print_bytes(TAG, bb, 16);
                throw "annexb not match for B, pos=";
            }

            // the start codes.
            /*(*bb).slice();
            for (int i = 0; i < tbbsc.nb_start_code; i++) {
                (*bb).get();
            }*/
            (*bb).position((*bb).position() + tbbsc.nb_start_code);

            // find out the frame size.
            tbb.data = (*bb).slice();
            int pos = (*bb).position();
            while ((*bb).position() < newSize) {
                SrsAnnexbSearch bsc = SrsUtils::srs_avc_startswith_annexb(bb, newOffset, newSize, newTimeUs,
                                                                          newFlags);
                if (bsc.match) {
                    break;
                }
                (*bb).get();
            }

            tbb.size = (*bb).position() - pos;
            break;
        }

        return tbb;
    }
};

jmethodID mid;

int achannel;
int asample_rate;
ByteBuffer h264_sps;
bool h264_sps_changed;
ByteBuffer h264_pps;
bool h264_pps_changed;
bool h264_sps_pps_sent;
BYTE* aac_specific_config;
bool needToFindKeyFrame = true;

void invokeFlvFrameCacheAddMethod(JNIEnv *env, jobject obj, int type, int dts, int frame_type, int avc_aac_type, BYTE* data, int size) {
jbyte* buff = (jbyte*)data;
jbyteArray jarray = env->NewByteArray(size);
env->SetByteArrayRegion(jarray, 0, size, buff);
env->CallVoidMethod(obj, mid, type, dts, frame_type, avc_aac_type, jarray, size);
}

void rtmp_write_packet(JNIEnv *env, jobject obj, int type, int dts, int frame_type, int avc_aac_type, BYTE* data, int size) {
if (type == Video) {
if (needToFindKeyFrame) {
if (frame_type == KeyFrame) {
needToFindKeyFrame = false;
invokeFlvFrameCacheAddMethod(env, obj, type, dts, frame_type, avc_aac_type, data, size);
}
} else {
invokeFlvFrameCacheAddMethod(env, obj, type, dts, frame_type, avc_aac_type, data, size);
}
} else if (type == Audio) {
invokeFlvFrameCacheAddMethod(env, obj, type, dts, frame_type, avc_aac_type, data, size);
}
}

void reset() {
    h264_sps_changed = false;
    h264_pps_changed = false;
    h264_sps_pps_sent = false;
    //aac_specific_config = NULL;
}
void stop() {
    reset();
    needToFindKeyFrame = true;
}

void setVideoTrack() {}

void setAudioTrack(int achannel1, int asample_rate1) {
    achannel = achannel1;
    asample_rate = asample_rate1;
}

void write_h264_sps_pps(JNIEnv *env, jobject obj, int dts, int pts) {
// when sps or pps changed, update the sequence header,
// for the pps maybe not changed while sps changed.
// so, we must check when each video ts message frame parsed.
if (h264_sps_pps_sent && !h264_sps_changed && !h264_pps_changed) {
return;
}
// when not got sps/pps, wait.
if (h264_pps.array() == NULL || h264_sps.array() == NULL) {
return;
}
// h264 raw to h264 packet.
vector<SrsFlvFrameBytes> frames;
SrsRawH264Stream::mux_sequence_header(&h264_sps, &h264_pps, dts, pts, &frames);

// h264 packet to flv packet.
int frame_type = KeyFrame;
int avc_packet_type = SequenceHeader;
SrsFlvFrameBytes flv_tag = SrsRawH264Stream::mux_avc2flv(&frames, frame_type, avc_packet_type, dts, pts);

// the timestamp in rtmp message header is dts.
rtmp_write_packet(env, obj, Video, dts, frame_type, avc_packet_type, flv_tag.data.array(), flv_tag.size);
// reset sps and pps.
h264_sps_changed = false;
h264_pps_changed = false;
h264_sps_pps_sent = true;
//Log.i(TAG, String.format("flv: h264 sps/pps sent, sps=%dB, pps=%dB", h264_sps.array().length, h264_pps.array().length));
}

void write_h264_ipb_frame(JNIEnv *env, jobject obj, vector<SrsFlvFrameBytes>* ibps, int frame_type, int dts, int pts) {
// when sps or pps not sent, ignore the packet.
// @see https://github.com/simple-rtmp-server/srs/issues/203
if (!h264_sps_pps_sent) {
return;
}
int avc_packet_type = NALU;
SrsFlvFrameBytes flv_tag = SrsRawH264Stream::mux_avc2flv(ibps, frame_type, avc_packet_type, dts, pts);
// the timestamp in rtmp message header is dts.
rtmp_write_packet(env, obj, Video, dts, frame_type, avc_packet_type, flv_tag.data.array(), flv_tag.size);
}

void writeAudioSample(JNIEnv *env, jobject obj, ByteBuffer* bb, int newOffset, int newSize, long newTimeUs, int newFlags) {
    int pts = (int) (newTimeUs / 1000);
    int dts = pts;

    int length = newSize + 2;
    BYTE* frame = new BYTE[length];
    BYTE aac_packet_type = 1; // 1 = AAC raw
    if (aac_specific_config == NULL) {
        length = 4;
        frame = new BYTE[4];

        // @see aac-mp4a-format-ISO_IEC_14496-3+2001.pdf
        // AudioSpecificConfig (), page 33
        // 1.6.2.1 AudioSpecificConfig
        // audioObjectType; 5 bslbf
        BYTE ch = (BYTE) ((*bb).get(0) & 0xf8);
        // 3bits left.

        // samplingFrequencyIndex; 4 bslbf
        BYTE samplingFrequencyIndex = 0x04;
        if (asample_rate == R22050) {
            samplingFrequencyIndex = 0x07;
        } else if (asample_rate == R11025) {
            samplingFrequencyIndex = 0x0a;
        }
        ch |= (samplingFrequencyIndex >> 1) & 0x07;
        frame[2] = ch;

        ch = (BYTE) ((samplingFrequencyIndex << 7) & 0x80);
        // 7bits left.

        // channelConfiguration; 4 bslbf
        BYTE channelConfiguration = 1;
        if (achannel == 2) {
            channelConfiguration = 2;
        }
        ch |= (channelConfiguration << 3) & 0x78;
        // 3bits left.

        // GASpecificConfig(), page 451
        // 4.4.1 Decoder configuration (GASpecificConfig)
        // frameLengthFlag; 1 bslbf
        // dependsOnCoreCoder; 1 bslbf
        // extensionFlag; 1 bslbf
        frame[3] = ch;

        aac_specific_config = frame;
        aac_packet_type = 0; // 0 = AAC sequence header
    } else {
        (*bb).get(frame, 2, length - 2);
    }

    BYTE sound_format = 10; // AAC
    BYTE sound_type = 0; // 0 = Mono sound
    if (achannel == 2) {
        sound_type = 1; // 1 = Stereo sound
    }
    BYTE sound_size = 1; // 1 = 16-bit samples
    BYTE sound_rate = 3; // 44100, 22050, 11025
    if (asample_rate == 22050) {
        sound_rate = 2;
    } else if (asample_rate == 11025) {
        sound_rate = 1;
    }

    // for audio frame, there is 1 or 2 bytes header:
    //      1bytes, SoundFormat|SoundRate|SoundSize|SoundType
    //      1bytes, AACPacketType for SoundFormat == 10, 0 is sequence header.
    BYTE audio_header = (BYTE) (sound_type & 0x01);
    audio_header |= (sound_size << 1) & 0x02;
    audio_header |= (sound_rate << 2) & 0x0c;
    audio_header |= (sound_format << 4) & 0xf0;

    frame[0] = audio_header;
    frame[1] = aac_packet_type;

    SrsFlvFrameBytes tag;
    tag.data = ByteBuffer::wrap(frame, length);
    tag.size = length;

    rtmp_write_packet(env, obj, Audio, dts, 0, aac_packet_type, tag.data.array(), tag.size);
}

void writeVideoSample(JNIEnv *env, jobject obj, ByteBuffer* bb, int newOffset, int newSize, long newTimeUs, int newFlags) {
    int pts = (int) (newTimeUs / 1000);
    int dts = (int) pts;

    vector<SrsFlvFrameBytes> ibps;
    int frame_type = InterFrame;

    // send each frame.
    while ((*bb).position() < newSize) {
SrsFlvFrameBytes frame = SrsRawH264Stream::annexb_demux(bb, newOffset, newSize, newTimeUs, newFlags);

        // 5bits, 7.3.1 NAL unit syntax,
        // H.264-AVC-ISO_IEC_14496-10.pdf, page 44.
        //  7: SPS, 8: PPS, 5: I Frame, 1: P Frame
        int nal_unit_type = (int) (frame.data.get(0) & 0x1f);
        if (nal_unit_type == SPS || nal_unit_type == PPS) {
            //Log.i(TAG, String.format("annexb demux %dB, pts=%d, frame=%dB, nalu=%d", bi.size, pts, frame.size, nal_unit_type));
        }

        // for IDR frame, the frame is keyframe.
        if (nal_unit_type == IDR) {
            frame_type = KeyFrame;
        }

        // ignore the nalu type aud(9)
        if (nal_unit_type == AccessUnitDelimiter) {
            continue;
        }
        // for sps
        if (SrsRawH264Stream::is_sps(&frame)) {
            if (!(frame.data == &h264_sps)) {
                BYTE sps[frame.size];
                frame.data.get(sps, frame.size);
                h264_sps_changed = true;
                h264_sps = ByteBuffer::wrap(sps, frame.size);
            }
            continue;
        }

        // for pps
        if (SrsRawH264Stream::is_pps(&frame)) {
            if (!(frame.data == &h264_pps)) {
                BYTE pps[frame.size];
                frame.data.get(pps, frame.size);
                h264_pps_changed = true;
                h264_pps = ByteBuffer::wrap(pps, frame.size);
            }
            continue;
        }
        // ibp frame.
        SrsFlvFrameBytes nalu_header = SrsRawH264Stream::mux_ibp_frame(&frame);
        ibps.push_back(nalu_header);
        ibps.push_back(frame);
    }

    write_h264_sps_pps(env, obj, dts, pts);

    write_h264_ipb_frame(env, obj, &ibps, frame_type, dts, pts);
}

/*
 * Class:     net_ossrs_yasea_flv_SrsFlv
 * Method:    reset
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_ossrs_yasea_flv_SrsFlv_reset
(JNIEnv *env, jobject obj)
{
reset();
}

/*
 * Class:     net_ossrs_yasea_flv_SrsFlv
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_ossrs_yasea_flv_SrsFlv_stop
(JNIEnv *env, jobject obj)
{
stop();
}

/*
 * Class:     net_ossrs_yasea_flv_SrsFlv
 * Method:    setVideoTrack
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_ossrs_yasea_flv_SrsFlv_setVideoTrack
(JNIEnv *env, jobject obj)
{
setVideoTrack();
}

/*
 * Class:     net_ossrs_yasea_flv_SrsFlv
 * Method:    setAudioTrack
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_net_ossrs_yasea_flv_SrsFlv_setAudioTrack
(JNIEnv *env, jobject obj, jint achannel, jint asample_rate)
{
setAudioTrack(achannel, asample_rate);
}

/*
 * Class:     net_ossrs_yasea_flv_SrsFlv
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_net_ossrs_yasea_flv_SrsFlv_init
(JNIEnv *env, jobject obj)
{
jclass cls = env->GetObjectClass(obj);
mid = env->GetMethodID(cls, "flvFrameCacheAdd", "(IIII[BI)V");
}

/*
 * Class:     net_ossrs_yasea_flv_SrsFlv
 * Method:    writeAudioSample
 * Signature: (Ljava/nio/ByteBuffer;IIJI)V
 */
JNIEXPORT void JNICALL Java_net_ossrs_yasea_flv_SrsFlv_writeAudioSample
(JNIEnv *env, jobject obj, jobject bb, jint newOffset, jint newSize, jlong newTimeUs, jint newFlags)
{
jbyte *buf = (jbyte*)env->GetDirectBufferAddress(bb);
jlong size = env->GetDirectBufferCapacity(bb);
BYTE* buff = (BYTE*)buf;
ByteBuffer data = ByteBuffer::wrap(buff, size);
writeAudioSample(env, obj, &data, newOffset, newSize, newTimeUs, newFlags);
}

/*
 * Class:     net_ossrs_yasea_flv_SrsFlv
 * Method:    writeVideoSample
 * Signature: (Ljava/nio/ByteBuffer;IIJI)V
 */
JNIEXPORT void JNICALL Java_net_ossrs_yasea_flv_SrsFlv_writeVideoSample
(JNIEnv *env, jobject obj, jobject bb, jint newOffset, jint newSize, jlong newTimeUs, jint newFlags)
{
jbyte *buf = (jbyte*)env->GetDirectBufferAddress(bb);
jlong size = env->GetDirectBufferCapacity(bb);
BYTE* buff = (BYTE*)buf;
ByteBuffer data = ByteBuffer::wrap(buff, size);
try {
writeVideoSample(env, obj, &data, newOffset, newSize, newTimeUs, newFlags);
} catch (exception e) {
}
}

#ifdef __cplusplus
}
#endif
