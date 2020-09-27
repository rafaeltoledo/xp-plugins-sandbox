package app.sandbox.xp.ui

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RetryNewsRequestStream {
    private val subject = BehaviorSubject.create<Unit>()

    fun retries(): Observable<Unit> = subject.hide()

    fun retry() {
        subject.onNext(Unit)
    }
}