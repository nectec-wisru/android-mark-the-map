/*
 * Copyright (c) 2017 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package th.or.nectec.marlo;

import java.util.List;

import th.or.nectec.marlo.model.MarloCoord;
import th.or.nectec.marlo.model.MarloPolygon;

final class PolygonUtils implements PointInHoleValidator {

    @Override
    public boolean inBoundary(MarloPolygon polygon, MarloCoord coordinate) {
        List<MarloCoord> boundary = polygon.getBoundary();

        int nvert = boundary.size();
        boolean isInBound = false;
        for (int i = 0, j = nvert - 1; i < nvert; j = i++) {
            if (((boundary.get(i).getLongitude() >= coordinate.getLongitude())
                    != (boundary.get(j).getLongitude() >= coordinate.getLongitude()))
                    && (coordinate.getLatitude() <= (boundary.get(j).getLatitude() - boundary.get(i).getLatitude())
                    * (coordinate.getLongitude() - boundary.get(i).getLongitude())
                    / (boundary.get(j).getLongitude() - boundary.get(i).getLongitude())
                    + boundary.get(i).getLatitude()))
                isInBound = !isInBound;
        }
        return isInBound;
    }
}
