/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.knowledgebase.aggregator.portlet;

import com.liferay.knowledgebase.admin.portlet.AdminPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.WindowState;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AggregatorPortlet extends AdminPortlet {

	protected int getStatus(PortletRequest portletRequest) {
		return WorkflowConstants.STATUS_APPROVED;
	}

	protected boolean isProcessActionRequest(ActionRequest actionRequest) {
		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("deleteComment") ||
			actionName.equals("subscribeArticle") ||
			actionName.equals("unsubscribeArticle") ||
			actionName.equals("updateComment")) {

			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isServeRSSMaximized(ResourceRequest resourceRequest) {
		PortletPreferences preferences = resourceRequest.getPreferences();

		String articleWindowState = preferences.getValue(
			"articleWindowState", WindowState.MAXIMIZED.toString());

		if (articleWindowState.equals(WindowState.MAXIMIZED.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

}