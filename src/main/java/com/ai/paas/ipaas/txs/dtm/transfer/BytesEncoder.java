package com.ai.paas.ipaas.txs.dtm.transfer;

import java.io.UnsupportedEncodingException;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class BytesEncoder implements Encoder<Object> {
	public BytesEncoder(VerifiableProperties props) {

	}

	@Override
	public byte[] toBytes(Object msg) {
		if(msg instanceof String){
			try {
				return ((String) msg).getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}else{
			return (byte[])msg;
		}
	}

}
