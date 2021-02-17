import { TestBed } from '@angular/core/testing';

import { DeAuthPageGuard } from './de-auth-page.guard';

describe('DeAuthPageGuard', () => {
  let guard: DeAuthPageGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(DeAuthPageGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
