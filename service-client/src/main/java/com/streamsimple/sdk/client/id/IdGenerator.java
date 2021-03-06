/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsimple.sdk.client.id;

import com.streamsimple.guava.common.annotations.VisibleForTesting;
import com.streamsimple.javautil.serde.SerdeUtils;

public class IdGenerator
{
  private long timestamp;
  private long counter;

  public IdGenerator()
  {
    timestamp = System.currentTimeMillis();
  }

  @VisibleForTesting
  protected IdGenerator(long timestamp)
  {
    this.timestamp = timestamp;
  }

  public Id nextId()
  {
    byte[] id = new byte[2 * SerdeUtils.NUM_BYTES_LONG];
    SerdeUtils.serializeLong(timestamp, 0, id);
    SerdeUtils.serializeLong(counter, SerdeUtils.NUM_BYTES_LONG, id);
    counter++;

    return new Id(id);
  }
}
