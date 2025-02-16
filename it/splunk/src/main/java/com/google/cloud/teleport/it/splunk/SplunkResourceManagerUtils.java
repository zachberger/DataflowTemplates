/*
 * Copyright (C) 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.teleport.it.splunk;

import static com.google.cloud.teleport.it.common.utils.ResourceManagerUtils.generatePassword;

import java.util.HashMap;
import java.util.Map;
import org.apache.beam.sdk.io.splunk.SplunkEvent;

/** Utilities for {@link SplunkResourceManager} implementations. */
public final class SplunkResourceManagerUtils {

  // Splunk event metadata keys
  private static final String SPLUNK_EVENT_KEY = "event";
  private static final String SPLUNK_HOST_KEY = "host";
  private static final String SPLUNK_INDEX_KEY = "index";
  private static final String SPLUNK_TIME_KEY = "time";
  private static final String SPLUNK_SOURCE_KEY = "source";
  private static final String SPLUNK_SOURCE_TYPE_KEY = "sourcetype";

  private static final int MIN_PASSWORD_LENGTH = 8;
  private static final int MAX_PASSWORD_LENGTH = 20;

  private SplunkResourceManagerUtils() {}

  public static Map<String, Object> splunkEventToMap(SplunkEvent event) {
    Map<String, Object> eventMap = new HashMap<>();
    eventMap.put(SPLUNK_EVENT_KEY, event.event());
    eventMap.put(SPLUNK_HOST_KEY, event.host());
    eventMap.put(SPLUNK_INDEX_KEY, event.index());
    eventMap.put(SPLUNK_SOURCE_KEY, event.source());
    eventMap.put(SPLUNK_SOURCE_TYPE_KEY, event.sourceType());
    eventMap.put(SPLUNK_TIME_KEY, event.time());

    return eventMap;
  }

  /**
   * Generates a secure, valid Splunk password.
   *
   * @return The generated password.
   */
  static String generateSplunkPassword() {
    int numLower = 2;
    int numUpper = 2;
    int numSpecial = 0;
    return generatePassword(
        MIN_PASSWORD_LENGTH,
        MAX_PASSWORD_LENGTH,
        numLower,
        numUpper,
        numSpecial,
        /* specialChars= */ null);
  }

  /**
   * Generates a secure, valid Splunk HEC authentication token.
   *
   * @return The generated password.
   */
  static String generateHecToken() {
    int numLower = 1;
    int numUpper = 1;
    int numSpecial = 0;
    return generatePassword(
        MIN_PASSWORD_LENGTH,
        MAX_PASSWORD_LENGTH,
        numLower,
        numUpper,
        numSpecial,
        /* specialChars= */ null);
  }
}
