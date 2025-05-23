/*
 *
 * Copyright (c) 2019 xp-dojo organisation and committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xpdojo.bank;

import org.concordion.api.ConcordionResources;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.time.Instant;

import static org.xpdojo.bank.Account.accountWithBalance;
import static org.xpdojo.bank.Money.ZERO;
import static org.xpdojo.bank.Money.amountOf;

@RunWith(ConcordionRunner.class)
@ConcordionResources(value = { "/concordion.css" })
public class ViewBalanceSlip {

	private Account account = accountWithBalance(ZERO, Instant::now);

	public void setCurrentBalance(long balance) {
		account = accountWithBalance(amountOf(balance), Instant::now);
	}
	
	public BalanceStatementFixture checkCurrentBalance(String isoUtcDateTime) throws IOException {
		return new BalanceStatementFixture(account, isoUtcDateTime);
	}

}