package org.apache.falcon.designer.storage.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.falcon.designer.storage.Storage;
import org.apache.falcon.designer.storage.StorageException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSStorage implements Storage {

  private FileSystem fs;
  private String basePath;
  private final String SEPERATOR = "/";
  private static final String basePathConfigName = "falcon.designer.hdfsstorage.defaultpath";

  HDFSStorage(Configuration conf) throws StorageException {
    try {
      this.fs = FileSystem.get(conf);
    } catch (IOException e) {
      throw new StorageException(e);
    }
    this.basePath = conf.get(basePathConfigName);
    if (this.basePath == null || this.basePath.isEmpty()) {
      throw new StorageException(basePathConfigName +  " cannot be empty");
    }

  }

  @Override
  public InputStream open(String namespace, String entity)
      throws StorageException {
    try {
      return fs.open(new Path(basePath + SEPERATOR + namespace + SEPERATOR
          + entity));

    } catch (IllegalArgumentException e) {
      throw new StorageException(e);
    } catch (IOException e) {
      throw new StorageException(e);
    }

  }

  @Override
  public OutputStream create(String namespace, String entity)
      throws StorageException {
    try {
      return fs.create(new Path(basePath + SEPERATOR + namespace + SEPERATOR
          + entity));
    } catch (IllegalArgumentException e) {
      throw new StorageException(e);
    } catch (IOException e) {
      throw new StorageException(e);
    }

  }

  @Override
  public void delete(String namespace, String entity) throws StorageException {
    try {
      fs.delete(
          new Path(basePath + SEPERATOR + namespace + SEPERATOR + entity), true);
    } catch (IllegalArgumentException e) {
      throw new StorageException(e);
    } catch (IOException e) {
      throw new StorageException(e);
    }

  }

}
