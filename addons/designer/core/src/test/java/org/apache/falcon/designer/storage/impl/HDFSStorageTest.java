package org.apache.falcon.designer.storage.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.falcon.designer.storage.StorageException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HDFSStorageTest {
  private MiniDFSCluster cluster;
  private HDFSStorage hdfsStorageInst;

  @BeforeClass
  public void setUpDFS() throws Exception {
    Configuration conf = new Configuration();
    conf.set("falcon.designer.hdfsstorage.defaultpath", "/tmp/");
    cluster = new MiniDFSCluster.Builder(conf).numDataNodes(2).build();
    cluster.waitActive();
    hdfsStorageInst = new HDFSStorage(conf);

  }

  @Test
  public void testCreate() {
    try {
      final String testNameSpace = "testNS";
      final String testEntity = "testEntity";
      OutputStream opStream = hdfsStorageInst.create(testNameSpace, testEntity);
      String testMessage = "testing HDFSStorage";
      byte[] outputByte = new byte[testMessage.length()];
      opStream.write(testMessage.getBytes());
      opStream.close();
      InputStream ipStream = hdfsStorageInst.open(testNameSpace, testEntity);
      ipStream.read(outputByte, 0, testMessage.length());
      ipStream.close();
      hdfsStorageInst.delete(testNameSpace, testEntity);
      try {
        hdfsStorageInst.open(testNameSpace, testEntity);
      } catch (StorageException ex) {
        Assert.assertEquals(ex.getCause().getClass(),
            FileNotFoundException.class);
      }
      Assert.assertEquals(new String(outputByte), testMessage);
    } catch (StorageException ex) {
      Assert.fail(ex.getMessage());
    } catch (IOException ex) {
      Assert.fail(ex.getMessage());
    }

  }
}
