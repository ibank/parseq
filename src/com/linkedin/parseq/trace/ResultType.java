/*
 * Copyright 2012 LinkedIn, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.linkedin.parseq.trace;

import com.linkedin.parseq.EarlyFinishException;
import com.linkedin.parseq.Task;

/**
 * @author Chris Pettitt (cpettitt@linkedin.com)
 */
public enum ResultType
{
  SUCCESS,
  ERROR,
  EARLY_FINISH,
  UNFINISHED;

  public static ResultType fromTask(final Task<?> task)
  {
    if (!task.isDone())
    {
      return UNFINISHED;
    }
    else if (task.isFailed())
    {
      if (task.getError() instanceof EarlyFinishException)
      {
        return EARLY_FINISH;
      }

      return ERROR;
    }

    return SUCCESS;
  }
}
